// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  9

import java.util.Scanner;

public class Assignment_3_qs_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for the number of non-negative integers
        System.out.print("Enter the number of non-negative integers: ");
        int n = scanner.nextInt();
        
        // If no numbers are provided, exit
        if (n <= 0) {
            System.out.println("The list must contain at least one non-negative integer.");
            scanner.close();
            return;
        }
        
        System.out.println("Enter " + n + " non-negative integers:");
        
        // Initialize 'max' to a value lower than any possible non-negative integer.
        // Since all numbers are non-negative, starting with -1 is safe.
        int max = -1;
        
        // Read each number and update 'max' accordingly.
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            
            // Validate that the input is non-negative.
            if (num < 0) {
                System.out.println("Invalid input. Please enter non-negative integers only.");
                scanner.close();
                return;
            }
            
            // Update max if the current number is greater than the current max.
            if (num > max) {
                max = num;
            }
        }
        
        // Display the largest number found.
        System.out.println("The largest number in the list is: " + max);
        scanner.close();
    }
}
