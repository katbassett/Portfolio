/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.strategy;

import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingTransaction;
import com.mycompany.parkinglot.parking.CarType;

/**
 *
 * @author katbassett
 */
public abstract class FactoryChargeStrategy implements FactoryParkingCharges {
    
    @Override
    public ParkingChargeStrategy getStrategy(ParkingLot lot, ParkingTransaction transaction) {
        if (lot.isSpecialDay(transaction.getTransactionDate())) {
            return new TimeSpecialDayStrategy();
        } else if (transaction.getVehicleType() == CarType.TRUCK) {
    return new VehicleTypeDayStrategy();
        }
        
        return new DefaultChargeStrategy();
    }
}
