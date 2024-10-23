/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.command.RegisterCustomerCommand;
import com.mycompany.parkinglot.parking.ParkingOffice;
import com.mycompany.parkinglot.parking.Customer;
import com.mycompany.parkinglot.parking.Address;
import java.util.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class RegisterCustomerCommandTest {

    private ParkingOffice office;
    private RegisterCustomerCommand registerCustomerCommand;

    @BeforeEach
    public void setUp() {
        Address officeAddress = new Address.Builder()
                .setStreetAddress1("444 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        office = new ParkingOffice("Test Office", officeAddress);

        registerCustomerCommand = new RegisterCustomerCommand(office);
    }

    @Test
    public void testExecute() {
        Properties properties = new Properties();
        properties.setProperty("name", "Joey Smith");
        properties.setProperty("streetAddress1", "456 5th St");
        properties.setProperty("streetAddress2", "");
        properties.setProperty("city", "Austin");
        properties.setProperty("state", "TX");
        properties.setProperty("zipCode", "78704");
        properties.setProperty("phone", "512-555-6767");

        String result = registerCustomerCommand.execute(properties);

        assertTrue(result.contains("Customer registered: CUS-"));

        Customer customer = office.getCustomer2("Joey Smith");
        assertNotNull(customer);
        assertEquals("Joey Smith", customer.getName());
    }

    @Test
    public void testMissingParameter() {
        Properties properties = new Properties();
        properties.setProperty("name", "Jane Doe");

        String result = registerCustomerCommand.execute(properties);

        assertEquals("Missing parameters.", result);
    }
}
