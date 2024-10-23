/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */
public abstract class Client {
    public static void main(String[] args) {
        Address address = new Address.Builder()
                .setStreetAddress1("122 Main St")
                .setCity("Austin")
                .setState("Texas")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);
        
        ParkingOfficeInterface clientProxy = new ParkingOfficeProxy(office, "client");
        ParkingOfficeInterface adminProxy = new ParkingOfficeProxy(office, "admin");

        clientProxy.getParkingOfficeName();
        clientProxy.getCustomer("CUS-001");

        adminProxy.register("John Doe", new Address.Builder()
                .setStreetAddress1("222 1st St")
                .setCity("Austin")
                .setState("Texas")
                .build(), "512-555-1234");
    }    
}
