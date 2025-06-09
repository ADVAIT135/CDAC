// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  8

import java.util.Scanner;

public class Assignment_3_qs_8 {
    public static void main(String[] args) {
        // Sample array provided in the problem statement.
        int[] arr = {3, 5, 7, 9, 11};
        
        // Scanner to read the target value from the user.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target sum value: ");
        int target = scanner.nextInt();

        boolean pairFound = false;
        System.out.println("Pairs of elements in the array whose sum equals " + target + ":");
        
        // Check every unique pair in the array using nested loops.
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + target);
                    pairFound = true;
                }
            }
        }
        
        // Inform the user if no valid pair was found.
        if (!pairFound) {
            System.out.println("No pair found that sums to " + target + ".");
        }
        
        scanner.close();
    }
}
