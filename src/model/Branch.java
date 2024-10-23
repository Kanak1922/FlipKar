package model;

import enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class Branch {
    private String branchName;
    private Map<VehicleType, Vehicle> vehicles;

    public Branch(String branchName) {
        this.branchName = branchName;
        this.vehicles = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleType(), vehicle);
    }

    public Vehicle getVehicle(VehicleType vehicleType) {
        return vehicles.get(vehicleType);
    }

    public Map<VehicleType, Vehicle> getVehicles() {
        return vehicles;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setVehicles(Map<VehicleType, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchName='" + branchName + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
