/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */

import com.mycompany.parkinglot.parking.Car;
import java.util.Objects;
import java.time.Instant;

public class ParkingPermit {

    private final String id;
    private final Car car;
    private Instant expirationDate;
    private final Instant registrationDate;

    public ParkingPermit(String id, Car car, Instant expirationDate, Instant registrationDate) {
        this.id = id;
        this.car = car;
        this.expirationDate = expirationDate;
        this.registrationDate = registrationDate;
    }

    public String getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public Instant getRegistrationDate() {
        return registrationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Parking Permit: "
                + "ID: " + id + '\''
                + ", Vehicle: " + car
                + ", Expiration Date: " + expirationDate
                + ", Registration Date:" + registrationDate;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingPermit that = (ParkingPermit) o;
        return id.equals(that.id) && car.equals(that.car) && expirationDate.equals(that.expirationDate) && registrationDate.equals(that.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, expirationDate, registrationDate);
    }
}
