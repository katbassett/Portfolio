/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot.observer;

import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.mockito.Mockito.*;

/**
 *
 * @author katbassett
 */
public class ParkingActionTest {
    
        @Test
    public void testUpdateCalled() {
        ParkingAction mockParkingAction = mock(ParkingAction.class);

        ParkingLot mockLot = mock(ParkingLot.class);
        ParkingPermit mockPermit = mock(ParkingPermit.class);
        Instant timeIn = Instant.now();
        Instant timeOut = null;
        ParkingEvent event = new ParkingEvent(mockLot, mockPermit, timeIn, timeOut);
        mockParkingAction.update(event);

        verify(mockParkingAction, times(1)).update(event);
    }
    
}
