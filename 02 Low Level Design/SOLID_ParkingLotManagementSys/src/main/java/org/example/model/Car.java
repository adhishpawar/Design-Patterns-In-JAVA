package org.example.model;

public class Car implements Vehicle{

    public String vehicleNumber;

    public Car(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String getVehicleType() {
        return "CAR";
    }
}
