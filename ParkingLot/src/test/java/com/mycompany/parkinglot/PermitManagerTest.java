/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Calendar;

public class PermitManagerTest {

    @Test
    public void testRegisterPermit() {
        PermitManager permitManager = new PermitManager();
        Car car = new Car("PERMIT-001", LocalDate.now().plusYears(1), "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-001");

        ParkingPermit permit = permitManager.register(car);

        assertNotNull(permit);
        assertEquals(car, permit.getCar());
        assertTrue(permit.getExpirationDate().after(Calendar.getInstance()));
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

        Calendar expiredDate = Calendar.getInstance();
        expiredDate.add(Calendar.YEAR, -1);
        permit = new ParkingPermit(permit.getId(), car, expiredDate, Calendar.getInstance());

        permitManager.getPermit(permit.getId()).setExpirationDate(expiredDate);

        assertFalse(permitManager.isPermitValid(permit.getId()));
    }
}
