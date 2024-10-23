/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.strategy;

import com.mycompany.parkinglot.parking.ParkingTransaction;

/**
 *
 * @author katbassett
 */
public class TimeSpecialDayStrategy implements ParkingChargeStrategy {
    @Override
    public double calculateRate(ParkingTransaction transaction) {
        double baseRate = transaction.getParkingLot().getBaseRate();
        double rate = baseRate;
        
        if (transaction.isPeakHours()) {
            rate *= 1.25;
        }
        
        if (transaction.isSpecialDay()) {
            rate *= 1.50;
        }
        
        return rate; 
    }
}
