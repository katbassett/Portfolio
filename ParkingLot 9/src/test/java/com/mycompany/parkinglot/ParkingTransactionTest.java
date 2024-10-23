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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

/**
 *
 * @author katbassett
 */
public class ParkingTransactionTest {

    @Test
    public void testParkingTransactionAttributes() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car = new Car("PERMIT-1234", expirationDate.atZone(java.time.ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-1234", car, expirationDate, registrationDate);
        
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;
        ParkingLot lot = new ParkingLot("LOT-001", address, 100, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.MARCH, 5));

        Money feeCharged = new Money(1500);
        Instant transactionDate = Instant.now();

        ParkingTransaction transaction = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit)
                .setParkingLot(lot)
                .setFeeCharged(feeCharged)
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();
        
        assertEquals(transactionDate, transaction.getTransactionDate());
        assertEquals(permit, transaction.getPermit());
        assertEquals(lot, transaction.getParkingLot());
        assertEquals(feeCharged, transaction.getFeeCharged());
    }

    @Test
    public void testToString() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-1234", car, expirationDate, registrationDate);
       
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();


        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;
        ParkingLot lot = new ParkingLot("LOT-001", address, 100, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.MARCH, 8));

        Money feeCharged = new Money(1500);
        Instant transactionDate = Instant.now();

        ParkingTransaction transaction = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit)
                .setParkingLot(lot)
                .setFeeCharged(feeCharged)
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();
        
        String expected = "Parking Transaction: Transaction Date: " + transactionDate
                + ", Permit: " + permit
                + ", Lot: " + lot
                + ", Fee: " + feeCharged;
        assertEquals(expected, transaction.toString());
    }

    @Test
    public void testEquals() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car1 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit1 = new ParkingPermit("PERMIT-1234", car1, expirationDate, registrationDate);
        
        Address address1 = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;
        ParkingLot lot1 = new ParkingLot("LOT-001", address1, 100, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024,  Month.MARCH, 9));

        Money feeCharged1 = new Money(1500);
        Instant transactionDate = Instant.now();

        ParkingTransaction transaction1 = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit1)
                .setParkingLot(lot1)
                .setFeeCharged(feeCharged1)
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();
        
        Car car2 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit2 = new ParkingPermit("PERMIT-1234", car2, expirationDate, registrationDate);

       ParkingTransaction transaction2 = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit2)
                .setParkingLot(lot1)
                .setFeeCharged(feeCharged1)
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();
       
        assertEquals(transaction1, transaction2); // should be true

        ParkingLot lot2 = new ParkingLot("LOT-002", address1, 200, "Lot B", 7.5, dummyStrategy, LocalDate.of(2024, Month.OCTOBER, 6));
        
        ParkingTransaction transaction3 = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit1)
                .setParkingLot(lot2)
                .setFeeCharged(feeCharged1)
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();

        assertNotEquals(transaction1, transaction3); // should be false
    }

    @Test
    public void testHashCode() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car1 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit1 = new ParkingPermit("PERMIT-1234", car1, expirationDate, registrationDate);
        Address address1 = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;
        
        ParkingLot lot1 = new ParkingLot("LOT-001", address1, 100, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, 07, 5));

        Money feeCharged1 = new Money(1500);
        Instant transactionDate = Instant.now();

        ParkingTransaction transaction1 = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit1)
                .setParkingLot(lot1)
                .setFeeCharged(feeCharged1)
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();

        Car car2 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit2 = new ParkingPermit("PERMIT-1234", car2, expirationDate, registrationDate);

        ParkingTransaction transaction2 = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit2)
                .setParkingLot(lot1)
                .setFeeCharged(feeCharged1)
                .setCarType(CarType.SUV)
                .setIsWeekend(false)
                .setIsPeakHours(true)
                .setIsSpecialDay(false)
                .build();

        assertEquals(transaction1.hashCode(), transaction2.hashCode());  // should be true
    }
}
