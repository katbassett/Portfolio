/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot.observer;

import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.TransactionManager;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.observer.ParkingEvent;
import com.mycompany.parkinglot.observer.ParkingObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 *
 * @author katbassett
 */
public class ParkingObserverTest {

    private TransactionManager transactionManager;
    private ParkingLot parkingLot;
    private ParkingPermit parkingPermit;
    private Car car;
    private ParkingObserver parkingObserver;

    @BeforeEach
    void setUp() {
        transactionManager = mock(TransactionManager.class);
        parkingLot = mock(ParkingLot.class);
        parkingPermit = mock(ParkingPermit.class);
        car = mock(Car.class);

        parkingObserver = new ParkingObserver(transactionManager, Collections.singletonList(parkingLot));
    }

    @Test
    public void testUpdate() {
        ParkingEvent event = mock(ParkingEvent.class);
        Instant timeIn = Instant.parse("2024-10-09T10:15:30.00Z");
        Instant timeOut = null;
        Money expectedCharge = new Money(10.0);

        when(event.getTimeIn()).thenReturn(timeIn);
        when(event.getTimeOut()).thenReturn(timeOut);
        when(event.getPermit()).thenReturn(parkingPermit);
        when(event.getLot()).thenReturn(parkingLot);
        when(parkingPermit.getCar()).thenReturn(car);

        when(car.getType()).thenReturn(CarType.SEDAN);

        when(parkingLot.calculateParkingRate(event)).thenReturn(10.0);

        parkingObserver.update(event);

        ArgumentCaptor<Instant> instantCaptor = ArgumentCaptor.forClass(Instant.class);
        ArgumentCaptor<ParkingPermit> permitCaptor = ArgumentCaptor.forClass(ParkingPermit.class);
        ArgumentCaptor<ParkingLot> lotCaptor = ArgumentCaptor.forClass(ParkingLot.class);
        ArgumentCaptor<Money> moneyCaptor = ArgumentCaptor.forClass(Money.class);
        ArgumentCaptor<CarType> carTypeCaptor = ArgumentCaptor.forClass(CarType.class);
        ArgumentCaptor<Boolean> weekendCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<Boolean> peakHoursCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<Boolean> specialDayCaptor = ArgumentCaptor.forClass(Boolean.class);

        verify(transactionManager).park(
                instantCaptor.capture(),
                permitCaptor.capture(),
                lotCaptor.capture(),
                moneyCaptor.capture(),
                carTypeCaptor.capture(),
                weekendCaptor.capture(),
                peakHoursCaptor.capture(),
                specialDayCaptor.capture()
        );

        System.out.println("Expected Car Type: " + CarType.SEDAN);
        System.out.println("Actual Car Type: " + carTypeCaptor.getValue());
        
        System.out.println("Captured weekend value: " + weekendCaptor.getValue());

        assertSame(CarType.SEDAN, carTypeCaptor.getValue());

        assertEquals(timeIn, instantCaptor.getValue());
        assertEquals(parkingPermit, permitCaptor.getValue());
        assertEquals(parkingLot, lotCaptor.getValue());
        assertEquals(expectedCharge, moneyCaptor.getValue());
        assertEquals(false, weekendCaptor.getValue());
        assertEquals(false, peakHoursCaptor.getValue());
        assertEquals(false, specialDayCaptor.getValue());
    }

}
