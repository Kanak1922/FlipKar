package model;

import enums.VehicleType;

public class Vehicle {
    private VehicleType vehicleType;
    private double pricePerHour;
    private int availableCount;

    public Vehicle(VehicleType vehicleType, double pricePerHour, int availableCount) {
        this.vehicleType = vehicleType;
        this.pricePerHour = pricePerHour;
        this.availableCount = availableCount;
    }

    public boolean isAvailable() {
        return availableCount > 0;
    }

    public void rent() {
        if (availableCount > 0) {
            availableCount--;
        } else {
            throw new RuntimeException("No vehicles available");
        }
    }

    public void returnVehicle() {
        availableCount++;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
    public void addQuantity(int quantity){
        this.availableCount+=quantity;
    }

}
