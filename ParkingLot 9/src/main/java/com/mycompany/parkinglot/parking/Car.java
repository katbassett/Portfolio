/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */
import java.time.LocalDate;
import java.util.Objects;

public class Car extends Vehicle {

    private final String permit;
    private final LocalDate permitExpiration;
    private final CarType type;
    private final String owner;

    public Car(String permit, LocalDate permitExpiration, String licensePlate, String model, CarType type, String owner) {
        super(licensePlate, model, type.toString());
        this.permit = permit;
        this.permitExpiration = permitExpiration;
        this.type = type;
        this.owner = owner;
    }

    public String getPermit() {
        return permit;
    }

    public LocalDate getPermitExpiration() {
        return permitExpiration;
    }

    public CarType getType() {
        return type;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Car"
                + "Permit: '" + permit + '\''
                + ", Permit Expiration: " + permitExpiration
                + ", Type: " + type
                + ", owner: '" + owner + '\''
                + ", Vehicle: License Plate: '" + getLicensePlate() + "', Model: '" + getModel() + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return permit.equals(car.permit)
                && permitExpiration.equals(car.permitExpiration)
                && type == car.type
                && owner.equalsIgnoreCase(car.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permit, permitExpiration, type, owner);
    }
}
