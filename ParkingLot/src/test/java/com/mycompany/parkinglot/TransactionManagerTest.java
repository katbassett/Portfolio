/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.time.LocalDate;

public class TransactionManagerTest {

    @Test
    public void testPark() {
        TransactionManager transactionManager = new TransactionManager();
        ParkingLot lot = new ParkingLot("LOT-001", new Address("123 Main St", "", "Austin", "TX", "78701"), 50);
        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-001", car, Calendar.getInstance(), Calendar.getInstance());
        Calendar parkDate = Calendar.getInstance();

        ParkingTransaction transaction = transactionManager.park(parkDate, permit, lot);

        assertNotNull(transaction);
        assertEquals(permit, transaction.getPermit());
        assertEquals(lot, transaction.getParkingLot());
        assertEquals(parkDate.toInstant(), transaction.getTransactionDate());
        assertEquals(new Money(1500), transaction.getFeeCharged());
    }

    @Test
    public void testGetParkingChargesByPermit() {
        TransactionManager transactionManager = new TransactionManager();
        ParkingLot lot = new ParkingLot("LOT-001", new Address("123 Main St", "", "Austin", "TX", "78701"), 50);
        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-001", car, Calendar.getInstance(), Calendar.getInstance());
        Calendar parkDate = Calendar.getInstance();

        transactionManager.park(parkDate, permit, lot);
        transactionManager.park(parkDate, permit, lot);

        Money totalCharges = transactionManager.getParkingCharges(permit);
        assertEquals(new Money(3000), totalCharges); // Assuming 2 transactions of 1500 each
    }

    @Test
    public void testGetParkingChargesByLicensePlate() {
        TransactionManager transactionManager = new TransactionManager();
        ParkingLot lot = new ParkingLot("LOT-001", new Address("123 Main St", "", "Austin", "TX", "78701"), 50);
        Car car1 = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit1 = new ParkingPermit("PERMIT-001", car1, Calendar.getInstance(), Calendar.getInstance());
        Car car2 = new Car("PERMIT-002", LocalDate.now().plusYears(1), "ABC-123", "Honda Accord", CarType.COMPACT, "CUS-002");
        ParkingPermit permit2 = new ParkingPermit("PERMIT-002", car2, Calendar.getInstance(), Calendar.getInstance());
        Calendar parkDate = Calendar.getInstance();

        transactionManager.park(parkDate, permit1, lot);
        transactionManager.park(parkDate, permit2, lot);
        transactionManager.park(parkDate, permit1, lot);

        Money chargesForCar1 = transactionManager.getParkingCharges("DMS-Z99");
        Money chargesForCar2 = transactionManager.getParkingCharges("ABC-123");

        assertEquals(new Money(3000), chargesForCar1); // Assuming 2 transactions of 1500 each
        assertEquals(new Money(1500), chargesForCar2); // Assuming 1 transaction of 1500
    }
}
