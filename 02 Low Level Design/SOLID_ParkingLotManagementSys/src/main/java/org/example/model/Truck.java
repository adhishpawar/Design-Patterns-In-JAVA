package org.example.model;

public class Truck implements Vehicle{

    public String vehicleNumber;

    public Truck(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String getVehicleType() {
        return "TRUCK";
    }
}
