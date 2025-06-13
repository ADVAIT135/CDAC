// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  4

// Define the enum for months of the year
enum Months {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
}

// Test class to print all months
public class Assignment_10_qs_4 {
    public static void main(String[] args) {
        for (Months month : Months.values()) {
            System.out.println(month);
        }
    }
}
