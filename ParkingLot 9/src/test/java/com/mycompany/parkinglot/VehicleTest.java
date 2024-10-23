/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.parking.Vehicle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class VehicleTest {

    @Test
    public void testVehicleAttributes() {
        Vehicle vehicle = new Vehicle("DMS-Z99", "Toyota Camry", "SEDAN");

        assertEquals("DMS-Z99", vehicle.getLicensePlate());
        assertEquals("Toyota Camry", vehicle.getModel());
    }

    @Test
    public void testToString() {
        Vehicle vehicle = new Vehicle("DMS-Z99", "Toyota Camry", "SEDAN");
        String expected = "Vehicle: License Plate: DMS-Z99'Model: Toyota Camry'";
        assertEquals(expected, vehicle.toString());
    }

    @Test
    public void testEquals() {
        Vehicle vehicle1 = new Vehicle("DMS-Z99", "Toyota Camry", "SEDAN");
        Vehicle vehicle2 = new Vehicle("DMS-Z99", "Toyota Camry", "SEDAN");
        Vehicle vehicle3 = new Vehicle("DDO-L33", "Honda Civic", "SEDAN");

        assertEquals(vehicle1, vehicle2); // should be true
        assertNotEquals(vehicle1, vehicle3); // should be false
    }

    @Test
    public void testHashCode() {
        Vehicle vehicle1 = new Vehicle("DMS-Z99", "Toyota Camry", "SEDAN");
        Vehicle vehicle2 = new Vehicle("DMS-Z99", "Toyota Camry", "SEDAN");

        int expectedHashCode = vehicle1.hashCode();
        assertEquals(expectedHashCode, vehicle2.hashCode());  // should be true
    }
}
