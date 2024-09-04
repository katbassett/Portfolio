/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void testParkingLotAttributes() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        ParkingLot parkingLot = new ParkingLot("LOT-001", address, 50);

        assertEquals("LOT-001", parkingLot.getLotId());
        assertEquals(address, parkingLot.getAddress());
        assertEquals(50, parkingLot.getCapacity());
    }

    @Test
    public void testEntry() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        ParkingLot parkingLot = new ParkingLot("LOT-001", address, 2);

        LocalDate expirationDate = LocalDate.now().plusYears(1);
        Car car1 = new Car("PERMIT-222", expirationDate, "ABC-D21", "Honda Accord", CarType.COMPACT, "CUS-003");
        Car car2 = new Car("PERMIT-333", expirationDate, "EDF-G43", "Ford Explorer", CarType.SUV, "CUS-004");

        parkingLot.entry(car1);
        parkingLot.entry(car2);

        assertEquals(2, parkingLot.getParkedCars().size());

        Car car3 = new Car("PERMIT-444", expirationDate, "HIJ-K56", "Tesla Model 3", CarType.COMPACT, "CUS-006");
        parkingLot.entry(car3);
        assertEquals(2, parkingLot.getParkedCars().size());
    }

    @Test
    public void testToString() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        ParkingLot parkingLot = new ParkingLot("LOT-001", address, 50);
        String expected = "ParkingLot{lotId='LOT-001', address=512 West Ave, Unit 550, Austin, TX, 78701, capacity=50, parkedCars=[]}";
        String actual = parkingLot.toString();

        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        Address address1 = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Address address2 = new Address("110 2nd St", "Unit 100", "Austin", "TX", "78703");

        ParkingLot parkingLot1 = new ParkingLot("LOT-001", address1, 50);
        ParkingLot parkingLot2 = new ParkingLot("LOT-001", address1, 50);
        ParkingLot parkingLot3 = new ParkingLot("LOT-003", address2, 30);

        assertEquals(parkingLot1, parkingLot2);
        assertNotEquals(parkingLot1, parkingLot3);
    }

    @Test
    public void testHashCode() {
        Address address1 = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Address address2 = new Address("110 2nd St", "Unit 100", "Austin", "TX", "78703");

        ParkingLot parkingLot1 = new ParkingLot("LOT-001", address1, 50);
        ParkingLot parkingLot2 = new ParkingLot("LOT-001", address1, 50);
        ParkingLot parkingLot3 = new ParkingLot("LOT-003", address2, 30);

        assertEquals(parkingLot1.hashCode(), parkingLot2.hashCode());
        assertNotEquals(parkingLot1.hashCode(), parkingLot3.hashCode());
    }
}
