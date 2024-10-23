/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.ParkingTransaction;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.parking.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Instant;
import java.time.LocalDate;

public class ParkingChargeStrategyTest {

    private ParkingChargeStrategy strategy;
    private ParkingTransaction mockTransaction;

    @BeforeEach
    public void setUp() {

        Instant timestamp = Instant.now();
        LocalDate registrationDate = LocalDate.now();
        var mockCar = new Car("Sedan", registrationDate, "Toyota", "DMM333", CarType.SEDAN, "Blue");
        ParkingPermit permit = new ParkingPermit("PERMIT-DMM333", mockCar, timestamp, timestamp.plusSeconds(3600));

        Address mockAddress = new Address.Builder()
                .setStreetAddress1("444 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();
        
        strategy = new MockParkingChargeStrategy();
        ParkingLot lot = new ParkingLot("Lot123", mockAddress, 100, "ZoneA", 2.50, strategy, LocalDate.of(2024, 10, 5));

        Money amount = new Money(0.0);
        String details = "Test Transaction";
        boolean isActive = true;
        boolean isPaid = false;
        boolean isCancelled = false;

        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(timestamp)
                .setPermit(permit)
                .setParkingLot(lot)
                .setFeeCharged(new Money(0.0))
                .setCarType(CarType.SEDAN)
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();


        System.out.println("Set up mock transaction for testing.");
    }

    private class MockParkingChargeStrategy implements ParkingChargeStrategy {

        @Override
        public double calculateRate(ParkingTransaction transaction) {

            return 5.0;
        }
    }

    @Test
    public void testCalculateRate() {
        double rate = strategy.calculateRate(mockTransaction);

        System.out.println("Calculated rate: " + rate);
        assertEquals(5.0, rate, "The calculated rate should be 5.0");
    }

}
