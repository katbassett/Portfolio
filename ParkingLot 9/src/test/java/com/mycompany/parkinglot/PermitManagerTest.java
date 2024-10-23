/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.parking.PermitManager;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PermitManagerTest {

    @Test
    public void testRegisterPermit() {
        PermitManager permitManager = new PermitManager();
        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");

        ParkingPermit permit = permitManager.register(car);

        assertNotNull(permit);
        assertEquals(car, permit.getCar());
        assertTrue(permit.getExpirationDate().isAfter(Instant.now()));
        assertNotNull(permit.getId());
    }

    @Test
    public void testGetPermit() {
        PermitManager permitManager = new PermitManager();
        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit registeredPermit = permitManager.register(car);

        ParkingPermit retrievedPermit = permitManager.getPermit(registeredPermit.getId());

        assertNotNull(retrievedPermit);
        assertEquals(registeredPermit, retrievedPermit);
    }

    @Test
    public void testIsPermitValid() {
        PermitManager permitManager = new PermitManager();
        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");
        ParkingPermit permit = permitManager.register(car);

        assertTrue(permitManager.isPermitValid(permit.getId()));

        ZonedDateTime expiredDateTime = Instant.now().atZone(ZoneId.systemDefault()).minus(1, ChronoUnit.YEARS);
        Instant expiredDate = expiredDateTime.toInstant();

        permitManager.getPermit(permit.getId()).setExpirationDate(expiredDate);

        assertFalse(permitManager.isPermitValid(permit.getId()));
    }
}
