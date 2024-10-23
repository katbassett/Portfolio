/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot.decorator;

import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.parking.Car;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author katbassett
 */
public class CompactCarDiscountDecoratorTest {

    private ParkingChargeCalculator mockCalculator;
    private CompactCarDiscountDecorator decorator;
    private ParkingLot mockParkingLot;
    private ParkingPermit mockPermit;
    private Instant transactionTime;
    private Car mockCar;

    @BeforeEach
    public void setUp() {
        mockCalculator = mock(ParkingChargeCalculator.class);
        mockParkingLot = mock(ParkingLot.class);
        mockPermit = mock(ParkingPermit.class);
        mockCar = mock(Car.class);
        transactionTime = Instant.now();

        decorator = new CompactCarDiscountDecorator(mockCalculator);

        when(mockPermit.getCar()).thenReturn(mockCar);
    }

    @Test
    public void testGetParkingCharge_CompactCarDiscount() {
        when(mockCar.getType()).thenReturn(CarType.COMPACT);
        when(mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit)).thenReturn(new Money(20.00));

        Money baseCharge = mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        System.out.println("Base parking charge before discount: $" + baseCharge.getDollars());

        Money parkingCharge = decorator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        System.out.println("Expected parking charge: $16.00, Actual: $" + parkingCharge.getDollars());

        assertEquals(new Money(16.00), parkingCharge, "The parking charge should be $16 with the compact car discount.");

    }

    @Test
    public void testGetParkingCharge_NonCompact() {
        when(mockCar.getType()).thenReturn(CarType.SUV);
        when(mockCalculator.getParkingCharge(transactionTime, mockParkingLot, mockPermit)).thenReturn(new Money(20.00));
        Money parkingCharge = decorator.getParkingCharge(transactionTime, mockParkingLot, mockPermit);
        assertEquals(new Money(20.00), parkingCharge, "The parking charge should be $16 with the compact car discount.");

    }

}
