package dto;

import enums.VehicleType;

public class VehicleDTO {
    private VehicleType vehicleType;
    private double pricePerHour;
    private int quantity;

    public VehicleDTO(VehicleType vehicleType, double pricePerHour, int quantity) {
        this.vehicleType = vehicleType;
        this.pricePerHour = pricePerHour;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicleType=" + vehicleType +
                ", pricePerHour=" + pricePerHour +
                ", quantity=" + quantity +
                '}';
    }
}
