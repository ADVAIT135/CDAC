// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 2 - Question -  8

import java.util.Scanner;

public class  Assignment_2_qs_8{

    // Method to compute the average of three numbers
    public static double computeAverage(double num1, double num2, double num3) {
        return (num1 + num2 + num3) / 3;
    }

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt and read the first number
        System.out.print("Enter first number: ");
        double number1 = scanner.nextDouble();

        // Prompt and read the second number
        System.out.print("Enter second number: ");
        double number2 = scanner.nextDouble();

        // Prompt and read the third number
        System.out.print("Enter third number: ");
        double number3 = scanner.nextDouble();

        // Calculate the average using the computeAverage method
        double average = computeAverage(number1, number2, number3);

        // Display the result to the user
        System.out.println("The average is: " + average);

        // Close the scanner to free up resources
        scanner.close();
    }
}
