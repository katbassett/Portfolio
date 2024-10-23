/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */

import java.time.Instant;
import com.mycompany.parkinglot.currency.Money;

public interface ParkingOfficeInterface {
    String getParkingOfficeName();
    Customer register(String name, Address address, String phone);
    Car registerCar(Customer customer, String license, String model, CarType type);
    Customer getCustomer(String customerId);
    ParkingTransaction park(Instant date, ParkingPermit permit, ParkingLot lot, Money feeCharged);
    Money getParkingCharges(ParkingPermit permit);
    Money getParkingCharges(Customer customer);
}
