/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.parkinglot.strategy;

import com.mycompany.parkinglot.parking.ParkingTransaction;

/**
 *
 * @author katbassett
 */
public interface ParkingChargeStrategy {
    double calculateRate(ParkingTransaction transaction);
}

