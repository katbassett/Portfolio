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

import java.time.Instant;

public class GameDaySurchargeDecorator extends ParkingChargeCalculatorDecorator {
    
    public GameDaySurchargeDecorator(ParkingChargeCalculator calculator) {
        super(calculator);
    }
    
     @Override
    public Money getParkingCharge(Instant incurred, ParkingLot lot, ParkingPermit permit) {
        Money baseCharge = calculator.getParkingCharge(incurred, lot, permit);

        if (lot.isGameDay(incurred)) {  
            return baseCharge.add(new Money(10.00)); // add $10 
        }

        return baseCharge;
    }
}
