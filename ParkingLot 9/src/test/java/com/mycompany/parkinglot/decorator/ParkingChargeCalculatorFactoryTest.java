/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot.decorator;

import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author katbassett
 */
public class ParkingChargeCalculatorFactoryTest {
    
    private ParkingLot mockParkingLot;
    private ParkingPermit mockPermit;
    private Car mockCar;
    private ParkingChargeCalculatorFactory factory;
    
    public ParkingChargeCalculatorFactoryTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockParkingLot = mock(ParkingLot.class);
        mockPermit = mock(ParkingPermit.class);
        mockCar = mock(Car.class);
        
        when(mockPermit.getCar()).thenReturn(mockCar);
        
        factory = new ParkingChargeCalculatorFactory();
    }
    
    @Test 
    public void testCreateCalculator_CompactCar_NoGame() {
        
        when(mockCar.getType()).thenReturn(CarType.COMPACT);
        
        Instant transactionTime = Instant.now();
        when(mockParkingLot.isGameDay(transactionTime)).thenReturn(false);
        
        ParkingChargeCalculator calculator = factory.createParkingChargeCalculator(mockParkingLot, mockPermit, transactionTime);
        
        assertTrue(calculator instanceof CompactCarDiscountDecorator, "Exected compact car discount decorator for compact car");
    }
    
}