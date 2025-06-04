// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 2 - Question -  4

import java.util.Scanner;

public class Assignment_2_qs_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter five numbers
        System.out.println("Enter five numbers:");
        double num1 = scanner.nextDouble();
        double num2 = scanner.nextDouble();
        double num3 = scanner.nextDouble();
        double num4 = scanner.nextDouble();
        double num5 = scanner.nextDouble();

        // Calculate the average
        double average = (num1 + num2 + num3 + num4 + num5) / 5;

        // Display the average
        System.out.println("The average of the five numbers is: " + average);

        scanner.close();
    }
}
