/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.parking.Address;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void testParkingLotAttributes() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();
        
        ParkingChargeStrategy dummyStrategy = transaction -> 10.0;
        ParkingLot parkingLot = new ParkingLot("LOT-001", address, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));

        assertEquals("LOT-001", parkingLot.getLotId());
        assertEquals(address, parkingLot.getAddress());
        assertEquals(50, parkingLot.getCapacity());
        assertEquals("Main Lot", parkingLot.getName());
        assertEquals(5.0, parkingLot.getBaseRate(), 0.01);
    }

    @Test
    public void testEntry() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();
        
        ParkingChargeStrategy dummyStrategy = transaction -> 10.0;
        ParkingLot parkingLot = new ParkingLot("LOT-001", address, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2022, Month.MARCH, 5));

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
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();
        
        ParkingChargeStrategy dummyStrategy = transaction -> 10.0;
        ParkingLot parkingLot = new ParkingLot("LOT-001", address, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(9, Month.MARCH, 27));
        String expected = "ParkingLot{lotId='LOT-001', address=512 West Ave, Unit 550, Austin, TX, 78701, capacity=50, parkedCars=[]}";
        String actual = parkingLot.toString();

        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        
        Address address1 = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        Address address2 = new Address.Builder()
                .setStreetAddress1("110 2nd St")
                .setStreetAddress2("Unit 100")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78703")
                .build();

        ParkingChargeStrategy dummyStrategy = transaction -> 10.0;
        ParkingLot parkingLot1 = new ParkingLot("LOT-001", address1, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));
        ParkingLot parkingLot2 = new ParkingLot("LOT-001", address1, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));
        ParkingLot parkingLot3 = new ParkingLot("LOT-003", address2, 30, "Lot B", 7.5, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));

        assertEquals(parkingLot1, parkingLot2);
        assertNotEquals(parkingLot1, parkingLot3);
    }

    @Test
    public void testHashCode() {
        Address address1 = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        Address address2 = new Address.Builder()
                .setStreetAddress1("110 2nd St")
                .setStreetAddress2("Unit 100")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78703")
                .build();

        ParkingChargeStrategy dummyStrategy = transaction -> 10.0;
        ParkingLot parkingLot1 = new ParkingLot("LOT-001", address1, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));
        ParkingLot parkingLot2 = new ParkingLot("LOT-001", address1, 50, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));
        ParkingLot parkingLot3 = new ParkingLot("LOT-003", address2, 30,"Lot B", 7.5, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));

        assertEquals(parkingLot1.hashCode(), parkingLot2.hashCode());
        assertNotEquals(parkingLot1.hashCode(), parkingLot3.hashCode());
    }
}
