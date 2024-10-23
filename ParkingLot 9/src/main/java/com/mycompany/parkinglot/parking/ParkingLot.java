/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.Address;
import com.mycompany.parkinglot.observer.ParkingAction;
import com.mycompany.parkinglot.observer.ParkingEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;

public class ParkingLot {

    private final String lotId;
    private final Address address;
    private final int capacity;
    private final List<Car> parkedCars;
    private final String name;
    private final double baseRate;
    private final ParkingChargeStrategy pricingStrategy;
    private final LocalDate specialDay;
    private final List<ParkingAction> observers;

    public ParkingLot(String lotId, Address address, int capacity, String name, double baseRate, ParkingChargeStrategy strategy, LocalDate specialDay) {
        this.lotId = lotId;
        this.address = address;
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
        this.name = name;
        this.baseRate = baseRate;
        this.pricingStrategy = strategy;
        this.specialDay = specialDay;
        this.observers = new ArrayList<>();
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

    public String getName() {
        return name;
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

    public double calculateParkingRate(ParkingEvent event) {

        ParkingTransaction transaction = new ParkingTransaction.Builder()
                .setTransactionDate(event.getTimeOut() != null ? event.getTimeOut() : event.getTimeIn())
                .setPermit(event.getPermit())
                .setParkingLot(event.getLot())
                .build();
        
        return pricingStrategy.calculateRate(transaction);
    }

    public double getBaseRate() {
        return baseRate;
    }

    private static final Set<LocalDate> SPECIAL_DAYS = Set.of(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 12, 25)
    );

    public boolean isSpecialDay(Instant transactionDate) {
        LocalDate date = transactionDate.atZone(ZoneId.systemDefault()).toLocalDate();
        return SPECIAL_DAYS.contains(date);
    }

    public void registerObserver(ParkingAction observer) {
        observers.add(observer);
    }

    public void unregisterObserver(ParkingAction observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ParkingEvent event) {
        for (ParkingAction observer : observers) {
            observer.update(event);
        }
    }
    
    private static final Set<LocalDate> GAME_DAYS = Set.of(
        LocalDate.of(2024, 7, 4),  // example game day
        LocalDate.of(2024, 11, 24) // example game day2
    );
    
    public boolean isGameDay(Instant transactionDate) {
        LocalDate date = transactionDate.atZone(ZoneId.systemDefault()).toLocalDate();
        return GAME_DAYS.contains(date);
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
