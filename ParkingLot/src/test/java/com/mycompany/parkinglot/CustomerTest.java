/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class CustomerTest {

    @Test
    public void testCustomerAttributes() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Customer customer = new Customer("CUS-002", "Jane Smith", address, "512-444-2233");

        assertEquals("CUS-002", customer.getCustomerId());
        assertEquals("Jane Smith", customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals("512-444-2233", customer.getPhoneNumber());
    }

    @Test
    public void testToString() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Customer customer = new Customer("CUS-002", "Jane Smith", address, "512-444-2233");
        String expected = "Customer{customerId='CUS-002', name='Jane Smith', address=512 West Ave, Unit 550, Austin, TX, 78701, phoneNumber=512-444-2233'}";
        assertEquals(expected, customer.toString());
    }

    @Test
    public void testEquals() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Customer customer1 = new Customer("CUS-002", "Jane Smith", address, "512-444-2233");
        Customer customer2 = new Customer("CUS-002", "Jane Smith", address, "512-444-2233");
        Customer customer3 = new Customer("CUS-334", "John Clark", address, "512-555-3343");

        assertEquals(customer1, customer2); //should be true
        assertNotEquals(customer1, customer3); //should be false
    }

    @Test
    public void testHashCode() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Customer customer1 = new Customer("CUS-002", "Jane Smith", address, "512-444-2233");
        Customer customer2 = new Customer("CUS-002", "Jane Smith", address, "512-444-2233");
        Customer customer3 = new Customer("CUS-334", "John Clark", address, "512-555-3343");

        assertEquals(customer1.hashCode(), customer2.hashCode()); //true
        assertNotEquals(customer1.hashCode(), customer3.hashCode()); //false
    }

}
