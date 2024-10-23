/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.parkinglot.strategy;



/**
 *
 * @author katbassett
 */

import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingTransaction;


public interface FactoryParkingCharges {
    ParkingChargeStrategy getStrategy(ParkingLot lot, ParkingTransaction transaction);
}