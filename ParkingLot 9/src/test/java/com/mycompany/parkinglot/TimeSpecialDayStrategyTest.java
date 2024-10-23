/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.strategy.TimeSpecialDayStrategy;
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
import java.time.Month;

public class TimeSpecialDayStrategyTest {

    private ParkingChargeStrategy strategy;
    private ParkingTransaction mockTransaction;

    @BeforeEach
    public void setUp() {
        Instant transactionDate = Instant.now();
        LocalDate registrationDate = LocalDate.now();

        Car mockCar = new Car("Sedan", registrationDate, "Toyota", "DMM333", CarType.SEDAN, "Blue");
        ParkingPermit permit = new ParkingPermit("PERMIT-DMM333", mockCar, transactionDate, transactionDate.plusSeconds(3600));
        Address mockAddress = new Address.Builder()
                .setStreetAddress1("444 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();
        strategy = new TimeSpecialDayStrategy();
        ParkingLot lot = new ParkingLot("Lot123", mockAddress, 100, "ZoneA", 10.0, strategy, LocalDate.of(2024, Month.MARCH, 8));
        Money feeCharged = new Money(0.0);

        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit)
                .setParkingLot(lot)
                .setFeeCharged(feeCharged)
                .setCarType(CarType.SEDAN)
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();
    }

    @Test
    public void testCalculateRate_NoPeakHours_NoSpecialDay() {
        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(10.0, rate, "Rate should be the base rate (10.0) when it's neither peak hours nor a special day.");
    }

    @Test
    public void testCalculateRate_PeakHours() {
        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(mockTransaction.getTransactionDate())
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(mockTransaction.getVehicleType())
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();

        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(12.5, rate, "Rate should be 1.25 times the base rate (12.5) during peak hours.");
    }

    @Test
    public void testCalculateRate_SpecialDay() {
        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(mockTransaction.getTransactionDate())
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(mockTransaction.getVehicleType())
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(true)
                .build();

        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(15.0, rate, "Rate should be 1.5 times the base rate (15.0) on a special day.");
    }

    @Test
    public void testCalculateRate_PeakHours_SpecialDay() {
        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(mockTransaction.getTransactionDate())
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(mockTransaction.getVehicleType())
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(true)
                .build();

        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(18.75, rate, "Rate should be 1.25 times 1.5 times the base rate (18.75) during peak hours on a special day.");
    }
}
