/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;


import com.mycompany.parkinglot.command.RegisterCustomerCommand;
import com.mycompany.parkinglot.command.Command;
import com.mycompany.parkinglot.parking.ParkingOffice;
import com.mycompany.parkinglot.parking.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class CommandTest {
    
    private Command command;
    
    @BeforeEach
    public void setUp() {
        Address address = new Address.Builder()
                .setStreetAddress1("444 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();
        
        ParkingOffice office = new ParkingOffice("Test Office", address);
        command = new RegisterCustomerCommand(office);
    }
    
    @Test
    public void testGetCommandName() {
        assertEquals("CUSTOMER", command.getCommandName());
    }
    
    @Test 
    public void testGetDisplayName() {
        assertEquals("Register Customer", command.getDisplayName());
    }
}
