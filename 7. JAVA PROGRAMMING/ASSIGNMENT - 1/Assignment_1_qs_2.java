// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 1 - Question -  2

import java.util.Scanner;

public class Assignment_1_qs_2 {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user.
        Scanner scanner = new Scanner(System.in);
        
        // Declare two integer variables a and b, and initialize them with user inputs.
        System.out.print("Enter first integer: ");
        int a = scanner.nextInt();
        
        System.out.print("Enter second integer: ");
        int b = scanner.nextInt();
        
        // Declare an integer variable 'sum' and assign the value of the addition of a and b.
        int sum = a + b;
        
        // Declare another integer variable 'diff' and assign the value of subtraction of a and b.
        int diff = a - b;
        
        // Output the results on separate lines with descriptive messages.
        System.out.println("Sum of a and b is: " + sum);
        System.out.println("Difference of a and b is: " + diff);
        
        // Close the scanner to free up resources.
        scanner.close();
    }
}
