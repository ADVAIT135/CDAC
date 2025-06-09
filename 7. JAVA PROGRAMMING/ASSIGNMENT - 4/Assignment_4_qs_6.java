// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  6

import java.util.Scanner;

public class Assignment_4_qs_6 {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for three numbers
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        System.out.print("Enter third number: ");
        double num3 = scanner.nextDouble();

        // Initialize greatest as the first number
        double greatest = num1;

        // Compare the second number with the current greatest
        if (num2 > greatest) {
            greatest = num2;
        }
        // Compare the third number with the current greatest
        if (num3 > greatest) {
            greatest = num3;
        }

        // Print the result
        System.out.println("The greatest number is: " + greatest);

        // Close the scanner to free system resources
        scanner.close();
    }
}
