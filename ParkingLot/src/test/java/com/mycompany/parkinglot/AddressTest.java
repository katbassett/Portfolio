/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class AddressTest {

    @Test
    public void testGetAddressInfo() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        String expected = "512 West Ave, Unit 550, Austin, TX, 78701";
        assertEquals(expected, address.getAddressInfo());
    }

    @Test
    public void testToString() {
        Address address = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        String expected = "512 West Ave, Unit 550, Austin, TX, 78701";
        assertEquals(expected, address.toString());
    }

    @Test
    public void testEquals() {
        Address address1 = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Address address2 = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Address address3 = new Address("353 East Ave", "Unit 650", "Houston", "TX", "77002");

        assertEquals(address1, address2); //should be true 
        assertNotEquals(address1, address3); //should be false
    }

    @Test
    public void testHashCode() {
        Address address1 = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Address address2 = new Address("512 West Ave", "Unit 550", "Austin", "TX", "78701");
        Address address3 = new Address("353 East Ave", "Unit 650", "Houston", "TX", "77002");

        assertEquals(address1.hashCode(), address2.hashCode()); //should be true
        assertNotEquals(address1.hashCode(), address3.hashCode()); //should be false
    }

}
