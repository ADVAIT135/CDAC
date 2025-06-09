// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  3

import java.util.Scanner;

public class Assignment_4_qs_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        
        System.out.println("Enter 5 numbers:");
        
        // Loop 5 times to get input from the user
        for (int i = 0; i < 5; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            int number = scanner.nextInt();
            sum += number;
        }
        
        // Calculate average as a double to include decimals if necessary
        double average = sum / 5.0;
        
        System.out.println("The sum is: " + sum);
        System.out.println("The average is: " + average);
        
        scanner.close();
    }
}
