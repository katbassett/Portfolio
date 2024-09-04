/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class CarTest {

    @Test
    public void testCarAttributes() {
        LocalDate expirationDate = LocalDate.now().plusYears(1);
        Car car = new Car("PERMIT-252", expirationDate, "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-002");

        assertEquals("PERMIT-252", car.getPermit());
        assertEquals(expirationDate, car.getPermitExpiration());
        assertEquals("DMS-Z99", car.getLicensePlate());
        assertEquals("Toyota Camry", car.getModel());
        assertEquals(CarType.SUV, car.getType());
        assertEquals("CUS-002", car.getOwner());
    }

    @Test
    public void testToString() {
        LocalDate expirationDate = LocalDate.now().plusYears(1);
        Car car = new Car("PERMIT-252", expirationDate, "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-002");
        String expected = "CarPermit: 'PERMIT-252', Permit Expiration: " + expirationDate
                + ", Type: SUV, owner: 'CUS-002', Vehicle: License Plate: 'DMS-Z99', Model: 'Toyota Camry'}";
        assertEquals(expected, car.toString());
    }

    @Test
    public void testEquals() {
        LocalDate expirationDate = LocalDate.now().plusYears(1);
        Car car1 = new Car("PERMIT-252", expirationDate, "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-002");
        Car car2 = new Car("PERMIT-252", expirationDate, "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-002");
        Car car3 = new Car("PERMIT-312", expirationDate, "GRS-G60", "Honda Civic", CarType.COMPACT, "CUS-332");

        assertEquals(car1, car2); // should be true
        assertNotEquals(car1, car3); // should be false
    }

    @Test
    public void testHashCode() {
        LocalDate expirationDate = LocalDate.now().plusYears(1);
        Car car1 = new Car("PERMIT-252", expirationDate, "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-002");
        Car car2 = new Car("PERMIT-252", expirationDate, "DMS-Z99", "Toyota Camry", CarType.SUV, "CUS-002");
        Car car3 = new Car("PERMIT-312", expirationDate, "GRS-G60", "Honda Civic", CarType.COMPACT, "CUS-332");

        assertEquals(car1.hashCode(), car2.hashCode());  // should be true
        assertNotEquals(car1.hashCode(), car3.hashCode());  // should be false
    }
}
