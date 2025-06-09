// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  7

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Assignment_3_qs_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input: number of elements and the array itself
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        // List to store the leader elements
        ArrayList<Integer> leadersList = new ArrayList<>();
        
        // The rightmost element is always a leader
        int maxFromRight = arr[n - 1];
        leadersList.add(maxFromRight);
        
        // Traverse the array from right to left
        for (int i = n - 2; i >= 0; i--) {
            // If the current element is greater than all elements seen so far,
            // it qualifies as a leader.
            if (arr[i] > maxFromRight) {
                maxFromRight = arr[i];
                leadersList.add(maxFromRight);
            }
        }
        
        // Reverse the list to restore the original order of appearance
        Collections.reverse(leadersList);
        
        // Output the leaders
        System.out.println("Leaders in the array:");
        for (int leader : leadersList) {
            System.out.print(leader + " ");
        }
        System.out.println(); // for a clean break in output
        
        scanner.close();
    }
}
