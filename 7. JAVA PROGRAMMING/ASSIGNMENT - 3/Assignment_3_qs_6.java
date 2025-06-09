// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  6


import java.util.Scanner;
import java.util.Arrays;

public class Assignment_3_qs_6{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements in the original array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Input the array elements
        int[] arr = new int[n];
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Ask for the element to remove from the array
        System.out.print("Enter the element to remove: ");
        int elementToRemove = scanner.nextInt();

        // Count how many times elementToRemove appears in the array
        int count = 0;
        for (int value : arr) {
            if (value == elementToRemove) {
                count++;
            }
        }

        // If the element is not found, print a message and terminate the program
        if (count == 0) {
            System.out.println("Element " + elementToRemove + " not found in the array.");
            scanner.close();
            return;
        }

        // Create a new array which will have a reduced size after removal
        int[] newArray = new int[arr.length - count];
        int index = 0;
        // Copy all elements except those equal to elementToRemove into newArray
        for (int value : arr) {
            if (value != elementToRemove) {
                newArray[index++] = value;
            }
        }

        // Display the new array
        System.out.println("Array after removing " + elementToRemove + ": " + Arrays.toString(newArray));
        scanner.close();
    }
}
