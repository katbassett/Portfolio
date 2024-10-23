/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.ParkingTransaction;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.parking.Address;
import com.mycompany.parkinglot.strategy.DefaultChargeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author katbassett
 */
public class DefaultChargeStrategyTest {

    private DefaultChargeStrategy strategy;
    private ParkingTransaction mockTransaction;

    @BeforeEach
    public void setUp() {
        strategy = new DefaultChargeStrategy();

        Address mockAddress = new Address.Builder()
                .setStreetAddress1("222 2nd St")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78703")
                .build();

        ParkingLot mockParkingLot = new ParkingLot("Lot001", mockAddress, 100, "Main Lot", 10.0, null, LocalDate.of(2024, 3, 20)); 

        Car mockCar = new Car("Sedan", LocalDate.now(), "Toyota", "DMM123", CarType.SEDAN, "CUS001");
        ParkingPermit permit = new ParkingPermit("PERMIT-DMM123", mockCar, Instant.now(), Instant.now().plusSeconds(3600));

        mockTransaction = new ParkingTransaction.Builder()
                .setTransactionDate(Instant.now())
                .setPermit(permit)
                .setParkingLot(mockParkingLot)
                .setFeeCharged(new Money(0))
                .setCarType(CarType.SEDAN)
                .setIsWeekend(false)
                .setIsPeakHours(false)
                .setIsSpecialDay(false)
                .build();
    }

    @Test
    public void testCalculateFee() {
        Money fee = strategy.calculateFee(mockTransaction);
        assertEquals(new Money(10.0), fee, "Expected fee to be equal to the base rate of 10.0.");
    }

    @Test
    public void testCalculateRate_UnsupportedOperation() {
        assertThrows(UnsupportedOperationException.class, () -> {
            strategy.calculateRate(mockTransaction);
        }, "Expected UnsupportedOperationException to be thrown for calculateRate.");
    }
}