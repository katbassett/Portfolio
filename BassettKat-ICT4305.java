Author // Kat Bassett

package edu.du.ict4305.myCalendar;

  public class MyDate {
    private int day;
    private int month;
    private int year;
  
    public MyDate() 
        this.day = 15;
        this.month = 2;
        this.year = 2003;
        this.julianNumber = toJulianNumber(this.day, this.month, this.year);
    }

 public MyDate(MyDate date) {
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
        this.julianNumber = date.julianNumber;
    }

public MyDate(int day, int month, int year) {

        
    if (isValidDate(day, month, year)) {
        this.day = day;
        this.month = month;
        this.year = year;
                this.julianNumber = toJulianNumber(this.day, this.month, this.year);
        } else {
            throw new IllegalArgumentException("Invalid date.");
        }
    }

   
        
    public int getDay() {
        return this.day;
    }

  
    public int getMonth() {
        return this.month;
    }

    
    public int getYear() {
        return this.year;
    }

   
    public static boolean isLeapYear(int year) {
       
    }

   
    public static int getLastDayOfMonth(int month, int year) {
       
    }

   
    private static int toJulianNumber(int day, int month, int year) {
        
    }

   
    private int[] fromJulianNumber() {
       
    }

    
    private boolean isValidDate(int day, int month, int year) {
       
    }

    
}

