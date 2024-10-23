package dto;

import enums.VehicleType;

public class AddVehicleDTO {
    private VehicleType vehicleType;
    private int quantity;

    public AddVehicleDTO(VehicleType vehicleType, int quantity) {
        this.vehicleType = vehicleType;
        this.quantity = quantity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getQuantity() {
        return quantity;
    }
}

