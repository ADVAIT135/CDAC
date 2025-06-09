// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  9

import java.util.Scanner;

public class Assignment_4_qs_9 {
    public static void main(String[] args) {
        // Create a Scanner object for input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt for and read the first number
        System.out.print("Enter the first number: ");
        int num1 = scanner.nextInt();
        
        // Prompt for and read the second number
        System.out.print("Enter the second number: ");
        int num2 = scanner.nextInt();
        
        // Calculate the sum of the two numbers
        int sum = num1 + num2;
        
        // Display the result
        System.out.println("The sum is: " + sum);
        
        // Close the scanner to free up resources
        scanner.close();
    }
}
