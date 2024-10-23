/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */
import java.util.Objects;

public class Vehicle {

    private final String licensePlate;
    private final String model;
    private final String vehicleType;

    public Vehicle(String licensePlate, String model, String vehicleType) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }
    
    public boolean isVehicleType(String type) {
        return this.vehicleType.equalsIgnoreCase(type);
    }

    @Override
    public String toString() {
        return "Vehicle: "
                + "License Plate: " + licensePlate + '\''
                + "Model: " + model + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return licensePlate.equals(vehicle.licensePlate) && model.equals(vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, model);
    }
}
