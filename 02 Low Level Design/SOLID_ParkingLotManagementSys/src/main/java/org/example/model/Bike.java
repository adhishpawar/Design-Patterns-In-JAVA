package org.example.model;

public class Bike implements Vehicle{

    public String vehicleNumber;
    public Bike(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }
    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String getVehicleType() {
        return "BIKE";
    }
}
