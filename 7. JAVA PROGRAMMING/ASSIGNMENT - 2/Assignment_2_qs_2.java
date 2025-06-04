// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 2 - Question -  2

import java.util.Scanner;

public class Assignment_2_qs_2 {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter a number
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Generate and print the multiplication table up to 10
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
        
        // Close the scanner to free up resources
        scanner.close();
    }
}
