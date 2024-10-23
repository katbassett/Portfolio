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
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.ParkingPermit;

import java.time.Instant;

public class FlatRateCalculator extends ParkingChargeCalculator {
    private final Money flatRate;
    
    public FlatRateCalculator(Money flatRate) {
        this.flatRate = flatRate;
    }
    
    @Override 
    public Money getParkingCharge(Instant incurred, ParkingLot lot, ParkingPermit permit) {
        return flatRate;
    }
}
