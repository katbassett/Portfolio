/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class ParkingChargeTest {

    @Test
    public void testInitialization() {
        Money amount = new Money(1500);
        Instant now = Instant.now();
        ParkingCharge charge = new ParkingCharge("PERMIT-002", "LOT-001", now, amount);

        assertEquals("PERMIT-002", charge.getPermitId());
        assertEquals("LOT-001", charge.getLotId());
        assertEquals(now, charge.getIncurred());
        assertEquals(amount, charge.getAmount());
    }

    @Test
    public void testToString() {
        Money amount = new Money(1500);
        Instant now = Instant.now();
        ParkingCharge charge = new ParkingCharge("PERMIT-002", "LOT-001", now, amount);

        String expected = "Parking Charge: permitId: PERMIT-002, lotId: LOT-001, incurred=" + now + ", amount=$15.0";
    }

    @Test
    public void testEquals() {
        Money amount1 = new Money(1500);
        Money amount2 = new Money(1500);
        Instant now = Instant.now();

        ParkingCharge charge1 = new ParkingCharge("PERMIT-002", "LOT-001", now, amount1);
        ParkingCharge charge2 = new ParkingCharge("PERMIT-002", "LOT-001", now, amount2);
        ParkingCharge charge3 = new ParkingCharge("PERMIT-332", "LOT-003", now, new Money(200));

        assertEquals(charge1, charge2);
        assertNotEquals(charge1, charge3);
    }

}
