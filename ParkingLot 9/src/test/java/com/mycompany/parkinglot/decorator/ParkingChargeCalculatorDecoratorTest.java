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
public class ParkingChargeCalculatorDecoratorTest {
    
    private ParkingChargeCalculator mockCalculator;
    private ParkingChargeCalculatorDecorator decorator;
    private ParkingLot mockParkingLot;
    private ParkingPermit mockPermit;
    private Instant transactionTime;
    
    public class TestDecorator extends ParkingChargeCalculatorDecorator {
        
        public TestDecorator(ParkingChargeCalculator calculator) {
            super(calculator);
        }
        
        @Override 
        public Money getParkingCharge(Instant incurred, ParkingLot lot, ParkingPermit permit) {
            return calculator.getParkingCharge(incurred, lot, permit);
        }
    }
    
    @BeforeEach
    public void setUp() {
        mockCalculator = mock(ParkingChargeCalculator.class);
        mockParkingLot = mock(ParkingLot.class);
        mockPermit = mock(ParkingPermit.class);
        transactionTime = Instant.now();
        
        decorator = new TestDecorator(mockCalculator);
        
    }
    @Test
    public void testGetParkingCharge() {
        when(mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit)).thenReturn(new Money(20.00));
        Money parkingCharge = decorator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        verify(mockCalculator, times(1)).getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        assertEquals(new Money(20.00), parkingCharge, "The parking charge should be the flat rate of $20");
        
    }
    
}
