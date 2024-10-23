/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.strategy;

import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.parking.ParkingTransaction;

/**
 *
 * @author katbassett
 */
public class VehicleTypeDayStrategy implements ParkingChargeStrategy {
    @Override
    public double calculateRate(ParkingTransaction transaction) {
        double baseRate = transaction.getParkingLot().getBaseRate();
        double rate = baseRate;
        
        if (transaction.getVehicleType().equals("Compact")) {
            rate *= 0.80; //discount for compact cars
        }
        
        if (transaction.isWeekend()){
            rate *= 0.90; //discount for weekends
        }
        
        return rate;
    }
}
