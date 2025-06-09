// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  2

import java.util.Scanner;

public class Assignment_4_qs_2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a year
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();

        // Check leap year conditions:
        // A year is a leap year if it's divisible by 400, OR
        // it's divisible by 4 but not by 100.
        boolean isLeap = (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
        
        if (isLeap) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }
        
        scanner.close();
    }
}
