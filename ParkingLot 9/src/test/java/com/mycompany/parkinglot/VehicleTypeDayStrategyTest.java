/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.strategy.VehicleTypeDayStrategy;
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

public class VehicleTypeDayStrategyTest {

    private ParkingChargeStrategy strategy;
    private ParkingTransaction mockTransaction;

    @BeforeEach
    public void setUp() {
        Instant transactionDate = Instant.now();
        LocalDate registrationDate = LocalDate.now();

        Car mockCar = new Car("Compact", registrationDate, "Toyota", "DMM333", CarType.COMPACT, "Blue");
        ParkingPermit permit = new ParkingPermit("PERMIT-DMM333", mockCar, transactionDate, transactionDate.plusSeconds(3600));
        
        Address mockAddress = new Address.Builder()
                .setStreetAddress1("444 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();
        
        strategy = new VehicleTypeDayStrategy();
        ParkingLot lot = new ParkingLot("Lot123", mockAddress, 100, "ZoneA", 10.0, strategy, LocalDate.of(2024, 10, 5));
        Money feeCharged = new Money(0.0);

        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit)
                .setParkingLot(lot)
                .setFeeCharged(feeCharged)
                .setCarType(CarType.COMPACT)
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();
    }

    @Test
    public void testCalculateRate_CompactCar_Weekday() {
        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(8.0, rate);
    }

    @Test
    public void testCalculateRate_CompactCar_Weekend() {
        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(mockTransaction.getTransactionDate())
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(mockTransaction.getVehicleType())
                .setIsWeekend(true)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();

        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(7.2, rate);
    }

    @Test
    public void testCalculateRate_NonCompactCar_Weekday() {
        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(mockTransaction.getTransactionDate())
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();

        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(10.0, rate);
    }

    @Test
    public void testCalculateRate_NonCompactCar_Weekend() {
        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(mockTransaction.getTransactionDate())
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(CarType.SUV)
                .setIsWeekend(true)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();

        double rate = strategy.calculateRate(mockTransaction);
        assertEquals(9.0, rate);
    }
}