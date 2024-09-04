/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juliannumber;

/**
 *
 * @author katbassett
 */
public class MyDate {
    
    private int julianNumber;
    
    public MyDate() {
        this.julianNumber = toJulianNumber(1, 1, 1970);
    }
    
    public MyDate( MyDate date) {
        this.julianNumber = date.julianNumber;
    }
    
    public MyDate( int day, int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12.");
        }
        if (day < 1 || day > getLastDayOfMonth(month, year)) {
            throw new IllegalArgumentException("Day of the month is out of range.");
        }
        this.julianNumber = toJulianNumber(day, month, year);
    }
    
    public int getDay() {
        return fromJulianNumber()[0];
    }
    
    public int getMonth() {
        return fromJulianNumber()[1];
    }
    
    public int getYear() {
        return fromJulianNumber()[2];
    }
    
    public static boolean isLeapYear( int year ) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }
    
    public static int getLastDayOfMonth( int month, int year ) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29: 28;
            default:
                return 31;
        }
    }
    
    private static int toJulianNumber(int day, int month, int year) {
        return JulianNumber.toJulianNumber(day, month, year);
    }
    
    private int[] fromJulianNumber() {
        return JulianNumber.fromJulianNumber(this.julianNumber);
    }
}
