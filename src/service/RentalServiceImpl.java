package service;

import dto.VehicleDTO;
import enums.VehicleType;
import exceptions.*;
import model.Branch;
import model.Vehicle;
import stratergy.VehicleSelectionStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class RentalServiceImpl implements RentalService {
    private Map<String, Branch> branches;
    private VehicleSelectionStrategy selectionStrategy; // Add this line

    public RentalServiceImpl(VehicleSelectionStrategy selectionStrategy) { // Modify constructor
        this.branches = new HashMap<>();
        this.selectionStrategy = selectionStrategy; // Assign the selection strategy
    }

    @Override
    public void addBranch(String branchName, List<VehicleDTO> vehicles) {
        Branch branch = new Branch(branchName);
        for (VehicleDTO vehicleDTO : vehicles) {
            branch.addVehicle(new Vehicle(vehicleDTO.getVehicleType(), vehicleDTO.getPricePerHour(), vehicleDTO.getQuantity()));
        }
        branches.put(branchName, branch);
    }

    @Override
    public void addVehicle(String branchName, VehicleDTO vehicleDTO) {
        Branch branch = branches.get(branchName);
        if (branch != null) {
            Vehicle vehicle = branch.getVehicle(vehicleDTO.getVehicleType());
            if (vehicle != null) {
                vehicle.returnVehicle();
            } else {
                branch.addVehicle(new Vehicle(vehicleDTO.getVehicleType(), vehicleDTO.getPricePerHour(), vehicleDTO.getQuantity()));
            }
        } else {
            throw new BranchNotFoundException("Branch '" + branchName + "' not found.");
        }
    }

    @Override
    public void rentVehicle(VehicleType vehicleType, String startTime, String endTime) {
        int durationInHours = calculateDuration(startTime, endTime);
        if (durationInHours < 1) {
            throw new InvalidBookingDurationException("Booking duration must be at least 1 hour.");
        }

        // Get a list of branches
        List<Branch> branchList = new ArrayList<>(branches.values());

        // Use the strategy to select the appropriate branch
        Branch selectedBranch = selectionStrategy.selectBranch(branchList, vehicleType);

        if (selectedBranch != null) {
            Vehicle vehicle = selectedBranch.getVehicle(vehicleType);
            if (vehicle != null && vehicle.isAvailable()) {
                vehicle.rent();
                System.out.println("Vehicle rented from branch: " + selectedBranch.getBranchName());
                return;
            }
        }
        throw new VehicleNotAvailableException("No vehicles of type '" + vehicleType + "' available for booking.");
    }

    @Override
    public void printSystemView(String startTime, String endTime) {
        System.out.println("--------------------- Printing System View -----------------------------------");
        System.out.println("System View from " + startTime + " to " + endTime + ":");
        for (Branch branch : branches.values()) {
            System.out.println(branch.getBranchName());
            for (Map.Entry<VehicleType, Vehicle> entry : branch.getVehicles().entrySet()) {
                Vehicle vehicle = entry.getValue();
                String availability = vehicle.isAvailable() ? "Available" : "All booked";
                if (availability.equals("Available")) {
                    System.out.println(vehicle.getVehicleType().toString().toLowerCase() + " is available for Rs. " + vehicle.getPricePerHour());
                } else {
                    System.out.println("All " + vehicle.getVehicleType().toString().toLowerCase() + " are booked.");
                }
            }
            System.out.println();
        }
    }

    private int calculateDuration(String startTime, String endTime) {
        try {
            String cleanedStartTime = cleanDateString(startTime);
            String cleanedEndTime = cleanDateString(endTime);

            String year = " " + LocalDate.now().getYear();

            cleanedStartTime += year;
            cleanedEndTime += year;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM h:mm a yyyy", Locale.ENGLISH);

            // Parsing the date/time strings
            LocalDateTime startDateTime = LocalDateTime.parse(cleanedStartTime, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(cleanedEndTime, formatter);

            if (startDateTime.isBefore(LocalDateTime.now())) {
                throw new BookingTimeInPastException("Booking start time cannot be in the past.");
            }
            if(endDateTime.isBefore(startDateTime)){
                throw new InvalidBookingTimeException("end time should be greater then start time");
            }

            long minutesBetween = ChronoUnit.MINUTES.between(startDateTime, endDateTime);


            if (minutesBetween < 60 || minutesBetween % 60 != 0) {
                throw new InvalidBookingDurationException("Booking duration must be at least 1 hour or in multiples of an hour.");
            }

            return (int) (minutesBetween / 60);

        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please provide the time in 'd MMM h:mm a' format.");
        }
    }

    private String cleanDateString(String dateStr) {
        return dateStr.replaceAll("(?<=\\d)(st|nd|rd|th)", "");
    }
}
