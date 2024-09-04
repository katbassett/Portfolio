/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.parkinglot;

/**
 *
 * @author katbassett
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingLot {

    private final String lotId;
    private final Address address;
    private final int capacity;
    private final List<Car> parkedCars;

    public ParkingLot(String lotId, Address address, int capacity) {
        this.lotId = lotId;
        this.address = address;
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
    }

    public String getLotId() {
        return lotId;
    }

    public Address getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    public void entry(Car car) {
        if (parkedCars.size() < capacity) {
            parkedCars.add(car);
            System.out.println("Car with license " + car.getLicensePlate() + " entered parking lot.");
        } else {
            System.out.println("Parking lot is full.");
        }
    }

    public boolean exit(Car car) {
        if (parkedCars.remove(car)) {
            System.out.println("Car with license " + car.getLicensePlate() + " left parking lot.");
            return true;
        } else {
            System.out.println("Car with license " + car.getLicensePlate() + " is not found in the parking lot.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "ParkingLot{"
                + "lotId='" + lotId + '\''
                + ", address=" + address
                + ", capacity=" + capacity
                + ", parkedCars=" + parkedCars
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingLot that = (ParkingLot) o;
        return capacity == that.capacity
                && lotId.equals(that.lotId)
                && address.equals(that.address)
                && parkedCars.equals(that.parkedCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotId, address, capacity, parkedCars);
    }

}
