/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot.observer;

import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
/**
 *
 * @author katbassett
 */
public class ParkingEventTest {
    
    @Test
    public void testParkingEventCreation() {
        ParkingLot mockLot = mock(ParkingLot.class);
        ParkingPermit mockPermit = mock(ParkingPermit.class);

        Instant timeIn = Instant.parse("2024-10-09T10:15:30.00Z");
        Instant timeOut = Instant.parse("2024-10-09T12:15:30.00Z");

        ParkingEvent event = new ParkingEvent(mockLot, mockPermit, timeIn, timeOut);

        assertEquals(mockLot, event.getLot());
        assertEquals(mockPermit, event.getPermit());
        assertEquals(timeIn, event.getTimeIn());
        assertEquals(timeOut, event.getTimeOut());
    }

        @Test
    public void testParkingEventWithNullTimeOut() {
        ParkingLot mockLot = mock(ParkingLot.class);
        ParkingPermit mockPermit = mock(ParkingPermit.class);

        Instant timeIn = Instant.parse("2024-10-09T10:15:30.00Z");
        Instant timeOut = null;

        ParkingEvent event = new ParkingEvent(mockLot, mockPermit, timeIn, timeOut);

        assertEquals(mockLot, event.getLot());
        assertEquals(mockPermit, event.getPermit());
        assertEquals(timeIn, event.getTimeIn());
        assertNull(event.getTimeOut());  // timeOut should be null
    }
    
}
