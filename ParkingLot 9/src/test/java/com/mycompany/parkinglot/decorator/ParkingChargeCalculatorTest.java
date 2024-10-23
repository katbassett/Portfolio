/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot.decorator;

import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author katbassett
 */
public class ParkingChargeCalculatorTest {

    private ParkingChargeCalculator mockCalculator;
    private ParkingLot mockParkingLot;
    private ParkingPermit mockPermit;
    private Instant transactionTime;

    @BeforeEach
    public void setUp() {
        mockParkingLot = mock(ParkingLot.class);
        mockPermit = mock(ParkingPermit.class);
        transactionTime = Instant.now();
        mockCalculator = mock(ParkingChargeCalculator.class);
    }

    @Test
    public void testGetParkingCharge() {
        when(mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit)).thenReturn(new Money(25.00));
        Money parkingCharge = mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        assertEquals(new Money(25.00), parkingCharge, "The parking charge should be $25");
        verify(mockCalculator, times(1)).getParkingCharge(transactionTime, mockParkingLot, mockPermit);
    }

}
