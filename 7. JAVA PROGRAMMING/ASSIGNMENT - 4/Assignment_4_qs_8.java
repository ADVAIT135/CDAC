// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  8

import java.util.Scanner;

public class Assignment_4_qs_8{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        // Store the sign to reapply later.
        int sign = number < 0 ? -1 : 1;
        // Work with the absolute value of the number.
        number = Math.abs(number);
        
        int reversed = 0;
        while (number > 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }
        
        // Reapply the sign if the original was negative.
        reversed *= sign;
        System.out.println("Reversed number: " + reversed);
        
        scanner.close();
    }
}
