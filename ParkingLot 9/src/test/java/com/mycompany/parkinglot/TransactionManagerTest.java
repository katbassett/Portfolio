/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.TransactionManager;
import com.mycompany.parkinglot.parking.ParkingTransaction;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.parking.Address;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.Instant;

public class TransactionManagerTest {

    @Test
    public void testPark() {
        TransactionManager transactionManager = new TransactionManager();

        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;

        Address lotAddress = new Address.Builder()
                .setStreetAddress1("123 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingLot lot = new ParkingLot("LOT-001", lotAddress, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, 10, 8));

        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");

        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60); // Add one year

        ParkingPermit permit = new ParkingPermit("PERMIT-001", car, expirationDate, registrationDate);

        Instant parkDate = Instant.now();

        CarType carType = CarType.SUV;
        boolean isWeekend = false;
        boolean isPeakHours = true;
        boolean isSpecialDay = false;

        ParkingTransaction transaction = transactionManager.park(parkDate, permit, lot, new Money(1500.0), carType, isWeekend, isPeakHours, isSpecialDay);

        assertNotNull(transaction);
        assertEquals(permit, transaction.getPermit());
        assertEquals(lot, transaction.getParkingLot());
        assertEquals(parkDate, transaction.getTransactionDate());
        assertEquals(new Money(1500), transaction.getFeeCharged());
    }

    @Test
    public void testGetParkingChargesByPermit() {

        TransactionManager transactionManager = new TransactionManager();

        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;

        Address lotAddress = new Address.Builder()
                .setStreetAddress1("123 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingLot lot = new ParkingLot("LOT-001", lotAddress, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, 10, 9));

        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");

        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60); // Add one year

        ParkingPermit permit = new ParkingPermit("PERMIT-001", car, expirationDate, registrationDate);

        Instant parkDate = Instant.now();

        CarType carType = CarType.SUV;
        boolean isWeekend = false;
        boolean isPeakHours = true;
        boolean isSpecialDay = false;

        transactionManager.park(parkDate, permit, lot, new Money(1500.0), carType, isWeekend, isPeakHours, isSpecialDay);
        transactionManager.park(parkDate, permit, lot, new Money(1500.0), carType, isWeekend, isPeakHours, isSpecialDay);

        Money totalCharges = transactionManager.getParkingCharges(permit);
        assertEquals(new Money(3000), totalCharges); // Assuming 2 transactions of 1500 each
    }

    @Test
    public void testGetParkingChargesByLicensePlate() {
        TransactionManager transactionManager = new TransactionManager();

        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;
        Address lotAddress = new Address.Builder()
                .setStreetAddress1("123 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingLot lot = new ParkingLot("LOT-001", lotAddress, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, 10, 3));

        Car car1 = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        Car car2 = new Car("PERMIT-002", LocalDate.now().plusYears(1), "ABC-123", "Honda Accord", CarType.COMPACT, "CUS-002");

        Instant registrationDate1 = Instant.now();
        Instant expirationDate1 = registrationDate1.plusSeconds(365 * 24 * 60 * 60); // Add one year
        ParkingPermit permit1 = new ParkingPermit("PERMIT-001", car1, expirationDate1, registrationDate1);

        Instant registrationDate2 = Instant.now();
        Instant expirationDate2 = registrationDate2.plusSeconds(365 * 24 * 60 * 60); // Add one year
        ParkingPermit permit2 = new ParkingPermit("PERMIT-002", car2, expirationDate2, registrationDate2);

        Instant parkDate = Instant.now();

        CarType carType1 = CarType.SUV;
        CarType carType2 = CarType.SEDAN;
        boolean isWeekend = false;
        boolean isPeakHours = true;
        boolean isSpecialDay = false;

        transactionManager.park(parkDate, permit1, lot, new Money(1500.0), carType1, isWeekend, isPeakHours, isSpecialDay);
        transactionManager.park(parkDate, permit2, lot, new Money(1500.0), carType2, isWeekend, isPeakHours, isSpecialDay);
        transactionManager.park(parkDate, permit1, lot, new Money(1500.0), carType1, isWeekend, isPeakHours, isSpecialDay);

        Money chargesForCar1 = transactionManager.getParkingCharges("DMS-Z99");
        Money chargesForCar2 = transactionManager.getParkingCharges("ABC-123");

        assertEquals(new Money(3000), chargesForCar1);
        assertEquals(new Money(1500), chargesForCar2);
    }
}
