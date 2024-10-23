/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.currency.Money;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class MoneyTest {

    @Test
    public void testGetDollars() {
        Money money = new Money(1500);
        assertEquals(15.00, money.getDollars());
    }

    @Test
    public void testGetCents() {
        Money money = new Money(1500);
        assertEquals(1500, money.getCents());
    }

    @Test
    public void testToString() {
        Money money = new Money(1500);
        assertEquals("$15.0", money.toString());
    }

    @Test
    public void testEquals() {
        Money money1 = new Money(1500);
        Money money2 = new Money(1500);
        Money money3 = new Money(300);

        assertEquals(money1, money2);
        assertNotEquals(money1, money3);
    }

    @Test
    public void testHashCode() {
        Money money1 = new Money(1500);
        Money money2 = new Money(1500);
        Money money3 = new Money(300);

        assertEquals(money1.hashCode(), money2.hashCode());
        assertNotEquals(money1.hashCode(), money3.hashCode());
    }
}
