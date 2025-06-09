// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  10

public class Assignment_4_qs_10 {
    public static void main(String[] args) {
        
        int year = 2025;
        
        // Check if the year is a leap year:
        // A year is a leap year if it is divisible by 4.
        // However, if the year is divisible by 100, it must also be divisible by 400.
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }
    }
}
