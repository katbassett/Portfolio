/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.parking.ParkingService;
import com.mycompany.parkinglot.command.RegisterCustomerCommand;
import com.mycompany.parkinglot.parking.ParkingOffice;
import com.mycompany.parkinglot.parking.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class ParkingServiceTest {

    private ParkingService service;
    private ParkingOffice office;
    private RegisterCustomerCommand registerCustomerCommand;

    @BeforeEach
    public void setUp() {
        Address address = new Address.Builder()
                .setStreetAddress1("444 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        office = new ParkingOffice("Test Office", address);
        service = new ParkingService(office);

        registerCustomerCommand = new RegisterCustomerCommand(office);
        service.registerCommand(registerCustomerCommand.getCommandName(), registerCustomerCommand);
    }

    @Test
    public void testRegisterCommand() {
        assertNotNull(service.performCommand("CUSTOMER", new String[]{"name=John", "streetAddress1=444 Main St", "streetAddress2=", "city=Austin", "state=TX", "zipCode=78701", "phone=512-222-3322"}));
    }

    @Test
    public void testPerformUnknownCommand() {
        String result = service.performCommand("UNKNOWN_COMMAND", new String[]{});
        assertEquals("Command not found.", result);
    }

}
