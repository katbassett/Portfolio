/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.juliannumber;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katbassett
 */
public class MyDateTest {
    
    private static Object[][] testData;
    
    
    @BeforeAll
    public static void setUpClass() {
        testData = new Object[][] {
                {5, 1, 50, 1738327},
                {1, 2, 1957, 2435871},
                {15, 3, 1492, 2266076},
                {25, 12, 2050, 2470166}
        };
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testDefaultConstructor() {
        MyDate instance = new MyDate();
        assertEquals(1, instance.getDay());
        assertEquals(1, instance.getMonth());
        assertEquals(1970, instance.getYear());
    }
    
    @Test
    public void testCopyConstructor() {
        for (Object[] data : testData) {
            int day = (int) data[0];
            int month = (int) data[1];
            int year = (int) data[2];
            MyDate date1 = new MyDate(day, month, year);
            MyDate date2 = new MyDate(date1);
            assertEquals(day, date2.getDay());
            assertEquals(month, date2.getMonth());
            assertEquals(year, date2.getYear());
        }
    }
    
    @Test
    public void testParameterConstructorValidDate() {
        for (Object[] data : testData) {
            int day = (int) data[0];
            int month = (int) data[1];
            int year = (int) data[2];
            MyDate instance = new MyDate(day, month, year);
            assertEquals(day, instance.getDay());
            assertEquals(month, instance.getMonth());
            assertEquals(year, instance.getYear());
        }
    }
    
    @Test
    public void testParameterConstructorInvalidMonth() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MyDate(25, 13, 2050);
        });
    }
    
    @Test
    public void testParameterConstructorInvalidDay() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MyDate(32, 1, 2050);
        });
    }
    
    @Test
    public void testGetDay() {
        for (Object[] data : testData) {
            int day = (int) data[0];
            int month = (int) data[1];
            int year = (int) data[2];
            MyDate instance = new MyDate(day, month, year);
            assertEquals(day, instance.getDay());
        }
    }

    /**
     * Test of getMonth method, of class MyDate.
     */
    @Test
    public void testGetMonth() {
        for (Object[] data : testData) {
            int day = (int) data[0];
            int month = (int) data[1];
            int year = (int) data[2];
            MyDate instance = new MyDate(day, month, year);
            assertEquals(month, instance.getMonth());
        }
    }

    /**
     * Test of getYear method, of class MyDate.
     */
    @Test
    public void testGetYear() {
        for (Object[] data : testData) {
            int day = (int) data[0];
            int month = (int) data[1];
            int year = (int) data[2];
            MyDate instance = new MyDate(day, month, year);
            assertEquals(year, instance.getYear());
        }
    }

    /**
     * Test of isLeapYear method, of class MyDate.
     */
    @Test
    public void testIsLeapYear() {
        assertTrue(MyDate.isLeapYear(2020));
        assertFalse(MyDate.isLeapYear(2019));
        assertTrue(MyDate.isLeapYear(2000));
        assertFalse(MyDate.isLeapYear(1900));
    }

    /**
     * Test of getLastDayOfMonth method, of class MyDate.
     */
    @Test
    public void testGetLastDayOfMonth() {
        assertEquals(31, MyDate.getLastDayOfMonth(1, 2020));
        assertEquals(29, MyDate.getLastDayOfMonth(2, 2020));
        assertEquals(28, MyDate.getLastDayOfMonth(2, 2019));
        assertEquals(30, MyDate.getLastDayOfMonth(4, 2020));
    }
}
