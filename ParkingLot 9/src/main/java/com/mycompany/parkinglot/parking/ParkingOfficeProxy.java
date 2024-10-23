/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.Instant;
import com.mycompany.parkinglot.currency.Money;

/**
 *
 * @author katbassett
 */
public class ParkingOfficeProxy implements ParkingOfficeInterface {

    private final ParkingOffice parkingOffice;
    private final String role;

    public ParkingOfficeProxy(ParkingOffice parkingOffice, String role) {
        this.parkingOffice = parkingOffice;
        this.role = role;
    }

    @Override
    public String getParkingOfficeName() {
        return parkingOffice.getParkingOfficeName();
    }

    @Override
    public Customer register(String name, Address address, String phone) {
        if (isAdmin()) {
            return parkingOffice.register(name, address, phone);
        } else {
            try {
                throw new IllegalAccessException("Only admin can register customers.");
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ParkingOfficeProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        }

        @Override
    public Car registerCar(Customer customer, String license, String model, CarType type) {
        if (isAdmin()) {
            return parkingOffice.registerCar(customer, license, model, type);
        } else {
            try {
                throw new IllegalAccessException("Only admin can register cars.");
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ParkingOfficeProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }
        
        @Override
    public Customer getCustomer(String customerId) {
        return parkingOffice.getCustomer(customerId);
    }
    @Override
    public ParkingTransaction park(Instant date, ParkingPermit permit, ParkingLot lot, Money feeCharged) {
        return parkingOffice.park(date, permit, lot, feeCharged); 
    }

    @Override
    public Money getParkingCharges(ParkingPermit permit) {
        return parkingOffice.getParkingCharges(permit); 
    }

    @Override
    public Money getParkingCharges(Customer customer) {
        if (isAdmin()) {
            return parkingOffice.getParkingCharges(customer);
        } else {
            try {
                throw new IllegalAccessException("Only admin can view all customer charges.");
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ParkingOfficeProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }

    private boolean isAdmin() {
        return "admin".equalsIgnoreCase(role);
    }
}
