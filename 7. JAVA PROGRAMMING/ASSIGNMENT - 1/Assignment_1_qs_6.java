// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 1 - Question -  6

import java.util.Scanner;

public class Assignment_1_qs_6 {
    public static void main(String[] args) {
        // Create a Scanner object to read from the standard input (keyboard)
        Scanner scanner = new Scanner(System.in);

        // Get the userID from the user
        System.out.print("Enter userID: ");
        String userID = scanner.nextLine();

        // Get the password from the user
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Get the name from the user
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        // Get the age from the user
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        // Display the collected details
        System.out.println("\nUser Details:");
        System.out.println("User ID: " + userID);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        // Close the scanner to free resources
        scanner.close();
    }
}
