// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 2 - Question -  3

import java.util.Scanner;

public class Assignment_2_qs_3{
    public static void main(String[] args) {
        // Create a Scanner object to accept user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the circle's radius: ");
        double radius = scanner.nextDouble();

        // Calculate the circle's properties
        double diameter = 2 * radius;
        double circumference = 2 * Math.PI * radius;
        double area = Math.PI * radius * radius;

        // Display the results to the user
        System.out.println("Diameter: " + diameter);
        System.out.println("Circumference: " + circumference);
        System.out.println("Area: " + area);

        // Close the scanner to free up resources
        scanner.close();
    }
}
