package service;

import dto.VehicleDTO;
import enums.VehicleType;

import java.util.List;

public interface RentalService {
    void addBranch(String branchName, List<VehicleDTO> vehicles);
    void addVehicle(String branchName, VehicleDTO vehicleDTO);
    void rentVehicle(VehicleType vehicleType, String startTime, String endTime);
    void printSystemView(String startTime, String endTime);
}
