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
public class GameDaySurchargeDecoratorTest {
    
    private GameDaySurchargeDecorator gameDaySurchargeDecorator;
    private ParkingChargeCalculator mockCalculator;
    private ParkingLot mockParkingLot;
    private ParkingPermit mockPermit;
    private Instant transactionTime;
    
    
    @BeforeEach
    public void setUp() {
        mockCalculator = mock(ParkingChargeCalculator.class);
        mockParkingLot = mock(ParkingLot.class);
        mockPermit = mock(ParkingPermit.class);
        transactionTime = Instant.now();
        gameDaySurchargeDecorator = new GameDaySurchargeDecorator(mockCalculator);
    }
    
    @Test
    public void testGetParkingCharge() {
        when(mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit)).thenReturn(new Money(20.00));
        when(mockParkingLot.isGameDay(transactionTime)).thenReturn(true);
        Money parkingCharge = gameDaySurchargeDecorator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        assertEquals(new Money(30.00), parkingCharge, "The parking charge should be $30 with game day surcharge.");
    }
    
    @Test 
    public void testGetParkingCharge_NotGameDay() {
        when(mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit)).thenReturn(new Money(20.00));
        when(mockParkingLot.isGameDay(transactionTime)).thenReturn(false);
        Money parkingCharge = gameDaySurchargeDecorator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        assertEquals(new Money(20.00), parkingCharge, "The parking charge should be the flat rate of $20.");
    }
}
