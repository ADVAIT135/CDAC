// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 1 - Question -  5

import java.util.Scanner;

public class Assignment_1_qs_5 {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner read = new Scanner(System.in);
        
        // Prompt the user for input
        System.out.print("Enter the value for a: ");
        int a = read.nextInt();
        
        System.out.print("Enter the value for b: ");
        int b = read.nextInt();
        
        // Compute values based on user input
        int c = a + 2;   // c becomes a plus 2
        int d = c + b;   // d becomes c plus b
        
        // Display the output
        System.out.println("Output: " + d);
        
        // Close the scanner
        read.close();
    }
}
