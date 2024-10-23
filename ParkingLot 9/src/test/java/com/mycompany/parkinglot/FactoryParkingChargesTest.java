/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.strategy.FactoryParkingCharges;
import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.strategy.TimeSpecialDayStrategy;
import com.mycompany.parkinglot.strategy.VehicleTypeDayStrategy;
import com.mycompany.parkinglot.strategy.DefaultChargeStrategy;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingTransaction;
import com.mycompany.parkinglot.parking.Address;
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.strategy.DefaultChargeStrategy;
import com.mycompany.parkinglot.strategy.FactoryParkingCharges;
import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.strategy.TimeSpecialDayStrategy;
import com.mycompany.parkinglot.strategy.VehicleTypeDayStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class FactoryParkingChargesTest {
    
    private FactoryParkingCharges factory;
    private ParkingLot mockParkingLot;
    private ParkingTransaction mockTransaction;

    private class TestFactoryParkingCharges implements FactoryParkingCharges {
    @Override
    public ParkingChargeStrategy getStrategy(ParkingLot lot, ParkingTransaction transaction) {
        if (lot.isSpecialDay(transaction.getTransactionDate())) {
            return new TimeSpecialDayStrategy();
        }
        if (transaction.getVehicleType() == CarType.TRUCK) {
            return new VehicleTypeDayStrategy();
        }

        return new DefaultChargeStrategy();
    }
}


    @BeforeEach
    public void setUp() {
        factory = new TestFactoryParkingCharges();

        Address mockAddress = new Address.Builder()
                .setStreetAddress1("123 Main St")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        mockParkingLot = new ParkingLot("Lot001", mockAddress, 100, "Main Lot", 10.0, null, LocalDate.of(2024, 5, 3));
        
        Car mockCar = new Car("SUV", LocalDate.now(), "Toyota", "DMM123", CarType.SUV, "CUS001");
        ParkingPermit permit = new ParkingPermit("PERMIT-DMM123", mockCar, Instant.now(), Instant.now().plusSeconds(3600));
        
        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(Instant.now())
                .setPermit(permit)
                .setParkingLot(mockParkingLot)
                .setFeeCharged(new Money(0))
                .setCarType(CarType.SEDAN)
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();
    }

    @Test
    public void testGetStrategy_SpecialDay() {
        Instant specialDay = LocalDate.of(2024, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        ParkingTransaction specialDayTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(specialDay)
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(mockTransaction.getVehicleType())
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(true)
                .build();

        ParkingChargeStrategy strategy = factory.getStrategy(mockParkingLot, specialDayTransaction);
        assertTrue(strategy instanceof TimeSpecialDayStrategy, "Expected TimeSpecialDayStrategy for a special day.");
    }

    @Test
    public void testGetStrategy_Truck() {
        ParkingChargeStrategy strategy = factory.getStrategy(mockParkingLot, mockTransaction);
        assertTrue(strategy instanceof VehicleTypeDayStrategy, "Expected VehicleTypeDayStrategy for a truck.");
    }

    @Test
    public void testGetStrategy_Default() {
        ParkingTransaction defaultTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(Instant.now())
                .setPermit(mockTransaction.getPermit())
                .setParkingLot(mockTransaction.getParkingLot())
                .setFeeCharged(mockTransaction.getFeeCharged())
                .setCarType(CarType.SEDAN)
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();

        ParkingChargeStrategy strategy = factory.getStrategy(mockParkingLot, defaultTransaction);
        assertTrue(strategy instanceof DefaultChargeStrategy, "Expected DefaultChargeStrategy for a non-special condition.");
    }
}