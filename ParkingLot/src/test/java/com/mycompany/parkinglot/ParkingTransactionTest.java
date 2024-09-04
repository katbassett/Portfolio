/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.Calendar;

/**
 *
 * @author katbassett
 */
public class ParkingTransactionTest {

    @Test
    public void testParkingTransactionAttributes() {
        Calendar registrationDate = Calendar.getInstance();
        Calendar expirationDate = (Calendar) registrationDate.clone();
        expirationDate.add(Calendar.YEAR, 1);

        Car car = new Car("PERMIT-1234", expirationDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-1234", car, expirationDate, registrationDate);
        Address address = new Address("512 West Ave", "", "Austin", "TX", "78701");
        ParkingLot lot = new ParkingLot("LOT-001", address, 100);
        Money feeCharged = new Money(1500);

        Instant transactionDate = Instant.now();
        ParkingTransaction transaction = new ParkingTransaction(transactionDate, permit, lot, feeCharged);

        assertEquals(transactionDate, transaction.getTransactionDate());
        assertEquals(permit, transaction.getPermit());
        assertEquals(lot, transaction.getParkingLot());
        assertEquals(feeCharged, transaction.getFeeCharged());
    }

    @Test
    public void testToString() {
        Calendar registrationDate = Calendar.getInstance();
        Calendar expirationDate = (Calendar) registrationDate.clone();
        expirationDate.add(Calendar.YEAR, 1);

        Car car = new Car("PERMIT-1234", expirationDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-1234", car, expirationDate, registrationDate);
        Address address = new Address("512 West Ave", "", "Austin", "TX", "78701");
        ParkingLot lot = new ParkingLot("LOT-001", address, 100);
        Money feeCharged = new Money(1500);

        Instant transactionDate = Instant.now();
        ParkingTransaction transaction = new ParkingTransaction(transactionDate, permit, lot, feeCharged);

        String expected = "Parking Transaction: Transaction Date: " + transactionDate
                + ", Permit: " + permit
                + ", Lot: " + lot
                + ", Fee: " + feeCharged;
        assertEquals(expected, transaction.toString());
    }

    @Test
    public void testEquals() {
        Calendar registrationDate = Calendar.getInstance();
        Calendar expirationDate = (Calendar) registrationDate.clone();
        expirationDate.add(Calendar.YEAR, 1);

        Car car1 = new Car("PERMIT-1234", expirationDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit1 = new ParkingPermit("PERMIT-1234", car1, expirationDate, registrationDate);
        Address address1 = new Address("512 West Ave", "", "Austin", "TX", "78701");
        ParkingLot lot1 = new ParkingLot("LOT-001", address1, 100);
        Money feeCharged1 = new Money(1500);

        Instant transactionDate = Instant.now();
        ParkingTransaction transaction1 = new ParkingTransaction(transactionDate, permit1, lot1, feeCharged1);

        Car car2 = new Car("PERMIT-1234", expirationDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit2 = new ParkingPermit("PERMIT-1234", car2, expirationDate, registrationDate);
        ParkingTransaction transaction2 = new ParkingTransaction(transactionDate, permit2, lot1, feeCharged1);

        assertEquals(transaction1, transaction2); // should be true

        ParkingLot lot2 = new ParkingLot("LOT-002", address1, 200);
        ParkingTransaction transaction3 = new ParkingTransaction(transactionDate, permit1, lot2, feeCharged1);

        assertNotEquals(transaction1, transaction3); // should be false
    }

    @Test
    public void testHashCode() {
        Calendar registrationDate = Calendar.getInstance();
        Calendar expirationDate = (Calendar) registrationDate.clone();
        expirationDate.add(Calendar.YEAR, 1);

        Car car1 = new Car("PERMIT-1234", expirationDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit1 = new ParkingPermit("PERMIT-1234", car1, expirationDate, registrationDate);
        Address address1 = new Address("512 West Ave", "", "Austin", "TX", "78701");
        ParkingLot lot1 = new ParkingLot("LOT-001", address1, 100);
        Money feeCharged1 = new Money(1500);

        Instant transactionDate = Instant.now();
        ParkingTransaction transaction1 = new ParkingTransaction(transactionDate, permit1, lot1, feeCharged1);

        Car car2 = new Car("PERMIT-1234", expirationDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit2 = new ParkingPermit("PERMIT-1234", car2, expirationDate, registrationDate);
        ParkingTransaction transaction2 = new ParkingTransaction(transactionDate, permit2, lot1, feeCharged1);

        assertEquals(transaction1.hashCode(), transaction2.hashCode());  // should be true
    }
}
