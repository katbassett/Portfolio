/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot;

/**
 *
 * @author katbassett
 */
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class PermitManager {

    private final HashMap<String, ParkingPermit> permits = new HashMap<>();

    public ParkingPermit register(Car car) {
        String id = UUID.randomUUID().toString();
        Calendar registrationDate = Calendar.getInstance();
        Calendar expirationDate = (Calendar) registrationDate.clone();
        expirationDate.add(Calendar.YEAR, 1);

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
        return permit.getExpirationDate().after(Calendar.getInstance());
    }
}
