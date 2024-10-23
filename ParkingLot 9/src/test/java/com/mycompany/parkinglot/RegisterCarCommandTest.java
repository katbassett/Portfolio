/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.command.RegisterCarCommand;
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
public class RegisterCarCommandTest {

    private ParkingOffice office;
    private RegisterCarCommand registerCarCommand;
    private Customer customer;

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

        registerCarCommand = new RegisterCarCommand(office);

        Address customerAddress = new Address.Builder()
                .setStreetAddress1("222 1st St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78703")
                .build();

        customer = office.register("John Doe", customerAddress, "552-888-8989");

        System.out.println("Registered customer ID: " + customer.getCustomerId());
    }

    @Test
    public void testExecute() {
        Properties properties = new Properties();
        properties.setProperty("customerId", customer.getCustomerId());
        properties.setProperty("license", "DMM333");
        properties.setProperty("model", "Sedan");
        properties.setProperty("carType", "SEDAN");

        System.out.println("Customer ID being passed: " + customer.getCustomerId());

        String result = registerCarCommand.execute(properties);

        System.out.println("Result: " + result);
        assertTrue(result.contains("Car registered with permit: PERMIT-DMM333"));
    }

    @Test
    public void testExecuteCustomerNotFound() {
        Properties properties = new Properties();
        properties.setProperty("customerId", "NON_EXISTENT");
        properties.setProperty("license", "DMM333");
        properties.setProperty("model", "Sedan");
        properties.setProperty("carType", "SEDAN");

        String result = registerCarCommand.execute(properties);
        assertEquals("Customer not found.", result);

        System.out.println("Result: " + result);
    }
}
