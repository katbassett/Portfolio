/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.observer;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;

import java.time.Instant;


public class ParkingEvent {
    private final ParkingLot lot;
    private final ParkingPermit permit;
    private final Instant timeIn;
    private final Instant timeOut;
    
    public ParkingEvent(ParkingLot lot, ParkingPermit permit, Instant timeIn, Instant timeOut) {
        this.lot = lot;
        this.permit = permit;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }
    
    public ParkingLot getLot() {
        return lot;
    }
    
    public ParkingPermit getPermit() {
        return permit;
    } 
    
    public Instant getTimeIn() {
        return timeIn;
    }
    
    public Instant getTimeOut() {
        return timeOut;
    }
}
