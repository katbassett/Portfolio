/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.strategy;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.parking.ParkingTransaction;
import com.mycompany.parkinglot.currency.Money;

public class DefaultChargeStrategy implements ParkingChargeStrategy {

    public Money calculateFee(ParkingTransaction transaction) {
        double baseRate = transaction.getParkingLot().getBaseRate();
        return new Money(Math.round(baseRate * 100) / 100.0); 
    }

    @Override
    public double calculateRate(ParkingTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
