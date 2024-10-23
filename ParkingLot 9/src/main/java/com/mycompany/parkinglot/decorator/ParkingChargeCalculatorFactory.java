/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.decorator;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.CarType;

import java.time.Instant;

public class ParkingChargeCalculatorFactory {

    public ParkingChargeCalculator createParkingChargeCalculator(ParkingLot lot, ParkingPermit permit, Instant transactionTime) {
        ParkingChargeCalculator calculator = new FlatRateCalculator(new Money(20.00));

        if (permit.getCar().getType() == CarType.COMPACT) {
            calculator = new CompactCarDiscountDecorator(calculator);
        }

        if (lot.isGameDay(transactionTime)) {
            calculator = new GameDaySurchargeDecorator(calculator);
        }

        return calculator;
    }
}
