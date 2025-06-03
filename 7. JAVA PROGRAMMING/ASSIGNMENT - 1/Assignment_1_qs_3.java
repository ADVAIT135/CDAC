// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 1 - Question -  3

import java.util.Scanner; // Import the Scanner class to read user input

public class Assignment_1_qs_3 {
    public static void main(String[] args) {
        // Create a Scanner object to capture user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their name
        System.out.print("Enter your name: ");

        // Read the user input and store it in a string variable x
        String x = scanner.nextLine();

        // Print "Hello" followed by the user-defined name, ensuring a space between "Hello" and the name
        System.out.println("Hello " + x);

        // Close the scanner to free resources
        scanner.close();
    }
}
