// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 1 - Question -  1

import java.util.Scanner;

public class Assignment_1_qs_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for an integer value
        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();

        // Calculate the square of the number
        int square = num * num;

        // Display the result
        System.out.println("Square of " + num + " is: " + square);

        // Close the Scanner
        scanner.close();
	}

}
