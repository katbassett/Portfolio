//Author

public class Main {
public static void main(String[] args) {
	
		MyDate date1 = new MyDate();
	    MyDate date2 = new MyDate(date1);
	    MyDate date3 = new MyDate(15, 8, 2023);

	       System.out.println("Date 1: " + date1.getDay() + "/" + date1.getMonth() + "/" + date1.getYear());
	        System.out.println("Date 2: " + date2.getDay() + "/" + date2.getMonth() + "/" + date2.getYear());
	        System.out.println("Date 3: " + date3.getDay() + "/" + date3.getMonth() + "/" + date3.getYear());

	        // Check if a year is a leap year
	        int year = 2024;
	        System.out.println(year + " is a leap year? " + MyDate.isLeapYear(year));

	        // Get the last day of a specific month and year
	      int month = 2;
	        int lastDay = MyDate.getLastDayOfMonth(month, year);
			System.out.println("Last day of month " + month + " in year " + year + ": " + lastDay);
	}
}