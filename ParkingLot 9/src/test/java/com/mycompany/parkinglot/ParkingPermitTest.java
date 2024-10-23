/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.ZoneId;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.ZonedDateTime;

public class ParkingPermitTest {

    @Test
    public void testParkingPermitAttributes() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-1234", car, expirationDate, registrationDate);

        assertEquals("PERMIT-1234", permit.getId());
        assertEquals(car, permit.getCar());
        assertEquals(expirationDate, permit.getExpirationDate());
        assertEquals(registrationDate, permit.getRegistrationDate());
    }

    @Test
    public void testSetExpirationDate() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-1234", car, expirationDate, registrationDate);

        ZonedDateTime newExpirationDateTime = permit.getExpirationDate().atZone(ZoneId.systemDefault()).plusYears(1);
        Instant newExpirationDate = newExpirationDateTime.toInstant();

        permit.setExpirationDate(newExpirationDate);
        assertEquals(newExpirationDate, permit.getExpirationDate());
    }

    @Test
    public void testToString() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = new ParkingPermit("PERMIT-1234", car, expirationDate, registrationDate);

        String expected = "Parking Permit: ID: PERMIT-1234', Vehicle: " + car + ", Expiration Date: " + expirationDate + ", Registration Date:" + registrationDate;
        assertEquals(expected, permit.toString());
    }

    @Test
    public void testEquals() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car1 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        Car car2 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");

        ParkingPermit permit1 = new ParkingPermit("PERMIT-1234", car1, expirationDate, registrationDate);
        ParkingPermit permit2 = new ParkingPermit("PERMIT-1234", car2, expirationDate, registrationDate);

        assertEquals(permit1, permit2); // should be true

        Car car3 = new Car("PERMIT-5678", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "XYZ-5678", "Honda Civic", CarType.COMPACT, "CUS-002");
        ParkingPermit permit3 = new ParkingPermit("PERMIT-5678", car3, expirationDate, registrationDate);

        assertNotEquals(permit1, permit3); // should be false
    }

    @Test
    public void testHashCode() {
        Instant registrationDate = Instant.now();
        Instant expirationDate = registrationDate.plusSeconds(365 * 24 * 60 * 60);

        Car car1 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        Car car2 = new Car("PERMIT-1234", expirationDate.atZone(ZoneId.systemDefault()).toLocalDate(), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");

        ParkingPermit permit1 = new ParkingPermit("PERMIT-1234", car1, expirationDate, registrationDate);
        ParkingPermit permit2 = new ParkingPermit("PERMIT-1234", car2, expirationDate, registrationDate);

        assertEquals(permit1.hashCode(), permit2.hashCode());  // should be true
    }
}
