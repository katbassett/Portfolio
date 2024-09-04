/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.juliannumber;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test; 

public class JulianNumberTest {
    
    @Test
    public void testToJulianNumber() {
        assertEquals(1739327, JulianNumber.toJulianNumber(5, 1, 50));
        assertEquals(2435871, JulianNumber.toJulianNumber(1, 2, 1957));
        assertEquals(2266076, JulianNumber.toJulianNumber(15, 3, 1492));
        assertEquals(2470166, JulianNumber.toJulianNumber(25, 12, 2050));
    }
    
    @Test
    public void testFromJulianNumber() {
        assertArrayEquals(new int[] { 5, 1, 50 }, JulianNumber.fromJulianNumber(1739327));
        assertArrayEquals(new int[] { 1, 2, 1957 }, JulianNumber.fromJulianNumber(2435871));
        assertArrayEquals(new int[] { 15, 3, 1492 }, JulianNumber.fromJulianNumber(2266076));
        assertArrayEquals(new int[] { 25, 12, 2050 }, JulianNumber.fromJulianNumber(2470166));
    }
}
