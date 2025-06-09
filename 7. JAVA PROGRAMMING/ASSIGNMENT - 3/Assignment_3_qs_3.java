// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  3

import java.util.Scanner;
import java.util.Arrays;

public class Assignment_3_qs_3 {
    
    // This method returns a new array after removing all occurrences of 'element'
    public static int[] removeElement(int[] arr, int element) {
        int count = 0;
        // Count occurrences of the element to remove
        for (int value : arr) {
            if (value == element) {
                count++;
            }
        }
        
        // Create a new array with the reduced size
        int[] newArray = new int[arr.length - count];
        int index = 0;
        // Copy over elements that are not equal to the element to remove
        for (int value : arr) {
            if (value != element) {
                newArray[index++] = value;
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the number of elements from the user
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        
        // Read the elements from the user
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        // Read the element to remove
        System.out.print("Enter the element you want to remove: ");
        int element = scanner.nextInt();
        
        // Remove the element and store the result in a new array
        int[] newArray = removeElement(arr, element);
        
        // Display the result
        System.out.println("Array after removing " + element + ": " + Arrays.toString(newArray));
        
        scanner.close();
    }
}
