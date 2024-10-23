package dto;

import enums.VehicleType;

public class BookingDTO {
    private VehicleType vehicleType;
    private String startTime;
    private String endTime;

    public BookingDTO(VehicleType vehicleType, String startTime, String endTime) {
        this.vehicleType = vehicleType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "vehicleType=" + vehicleType +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
