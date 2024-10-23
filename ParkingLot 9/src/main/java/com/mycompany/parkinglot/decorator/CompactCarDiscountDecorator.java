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

public class CompactCarDiscountDecorator extends ParkingChargeCalculatorDecorator {
    
    public CompactCarDiscountDecorator(ParkingChargeCalculator calculator) {
        super(calculator);
    }
    
    @Override 
    public Money getParkingCharge(Instant incurred, ParkingLot lot, ParkingPermit permit) {
        Money baseCharge = calculator.getParkingCharge(incurred, lot, permit);
        
        if (permit.getCar().getType() == CarType.COMPACT) {
            return baseCharge.multiply(0.8); // compact discount
        }

        return baseCharge;
    }
}
