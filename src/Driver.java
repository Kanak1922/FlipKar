
import dto.AddVehicleDTO;
import dto.VehicleDTO;
import enums.VehicleType;
import service.RentalService;
import service.RentalServiceImpl;
import stratergy.LowestPriceStrategy;
import stratergy.VehicleSelectionStrategy;

import java.util.Arrays;


public class Driver {
    public static void main(String[] args) {

        VehicleSelectionStrategy selectionStrategy = new LowestPriceStrategy();

        RentalService rentalService = new RentalServiceImpl(selectionStrategy);

        rentalService.addBranch("Koramangala", Arrays.asList(
                new VehicleDTO(VehicleType.SUV, 12, 1),
                new VehicleDTO(VehicleType.SEDAN, 10, 3),
                new VehicleDTO(VehicleType.BIKE, 20, 3)
        ));

        rentalService.addBranch("Jayanagar", Arrays.asList(
                new VehicleDTO(VehicleType.SEDAN, 11, 3),
                new VehicleDTO(VehicleType.BIKE, 30, 3),
                new VehicleDTO(VehicleType.HATCHBACK, 8, 4)
        ));

        rentalService.addBranch("Malleshwaram", Arrays.asList(
                new VehicleDTO(VehicleType.SUV, 11, 1),
                new VehicleDTO(VehicleType.BIKE, 3, 10),
                new VehicleDTO(VehicleType.SEDAN, 10, 3)
        ));


        rentalService.addVehicle("Koramangala", new VehicleDTO(VehicleType.SEDAN,10, 1)); // Adds 1 sedan to Koramangala
        ;


        try {
            rentalService.rentVehicle(VehicleType.SUV, "20th Feb 10:00 AM", "20th Feb 12:00 PM");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            rentalService.rentVehicle(VehicleType.SUV, "24th Oct 10:00 AM", "24th Oct 1:50 PM");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            rentalService.rentVehicle(VehicleType.SUV, "24th Oct 10:00 AM", "24th Oct 09:00 AM");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        rentalService.printSystemView("20th Feb 11:00 AM", "20th Feb 12:00 PM");
    }
}

