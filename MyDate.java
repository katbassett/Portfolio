//Author: Kat Bassett



public class MyDate {
    private int julianNumber;

    public MyDate() {
        julianNumber = toJulianNumber(1, 1, 1970);
    }

    public MyDate(MyDate date) {
        julianNumber = date.julianNumber;
    }

    public MyDate(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            julianNumber = toJulianNumber(day, month, year);
        } else {
            System.out.println("Invalid date");
            // You can throw an exception here or handle the error in a different way based on your requirements.
        }
    }
public int getDay() {
        int[] date = fromJulianNumber();
        return date[0];
    }

    public int getMonth() {
        int[] date = fromJulianNumber();
        return date[1];
    }

    public int getYear() {
        int[] date = fromJulianNumber();
        return date[2];
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static int getLastDayOfMonth(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }
private static int toJulianNumber(int day, int month, int year) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        return day + ((153 * m + 2) / 5) + (365 * y) + (y / 4) - (y / 100) + (y / 400) - 32045;
    }

private int[] fromJulianNumber() {
    int a = julianNumber + 32044;
    int b = (4 * a + 3) / 146097;
    int c = a - (146097 * b) / 4;
    int d = (4 * c + 3) / 1461;
    int e = c - (1461 * d) / 4;
    int m = (5 * e + 2) / 153;
    int day = e - ((153 * m + 2) / 5) + 1;
    int month = m + 3 - 12 * (m / 10);
    int year = 100 * b + d - 4800 + (m / 10);
    return new int[]{day, month, year};
}

private static boolean isValidDate(int day, int month, int year) {
    if (month < 1 || month > 12 || day < 1 || day > 31) {
        return false;
    }

    int lastDay = getLastDayOfMonth(month, year);
    return day <= lastDay;
}
	}
