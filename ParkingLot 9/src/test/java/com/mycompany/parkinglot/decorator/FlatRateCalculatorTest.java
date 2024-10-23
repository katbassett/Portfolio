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
public class FlatRateCalculatorTest {
    
    private FlatRateCalculator flatRateCalculator;
    private ParkingLot mockParkingLot;
    private ParkingPermit mockPermit;
    private Instant transactionTime;
    private Money flatRate;
    
    
    
    @BeforeEach
    public void setUp() {
        flatRate = new Money(20.00);
        flatRateCalculator = new FlatRateCalculator(flatRate);
        mockParkingLot = mock(ParkingLot.class);
        mockPermit = mock(ParkingPermit.class);
        transactionTime = Instant.now();
    }
    
    
    @Test
    public void testGetParkingCharge_ReturnsFlatRate() {
        
        Money parkingCharge = flatRateCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        assertEquals(flatRate, parkingCharge, "The parkingcharge should be the flat rate of $20");
    }
    
}
