/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */

import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.Car;
import java.util.HashMap;
import java.util.UUID;
import java.time.Instant; 
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PermitManager {

    private final HashMap<String, ParkingPermit> permits = new HashMap<>();

    public ParkingPermit register(Car car) {
        String id = UUID.randomUUID().toString();
        
        Instant registrationDate = Instant.now();
       
        ZonedDateTime expirationDateTime = registrationDate.atZone(ZoneId.systemDefault()).plus(1, ChronoUnit.YEARS);
        Instant expirationDate = expirationDateTime.toInstant();
        
        ParkingPermit permit = new ParkingPermit(id, car, expirationDate, registrationDate);
        permits.put(id, permit);
        return permit;
    }

    public ParkingPermit getPermit(String id) {
        return permits.get(id);
    }

    public boolean isPermitValid(String id) {
        ParkingPermit permit = permits.get(id);
        if (permit == null) {
            return false;
        }
        return permit.getExpirationDate().isAfter(Instant.now());
    }
}
