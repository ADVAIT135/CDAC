// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  4

import java.util.Scanner;
import java.util.Arrays;

public class Assignment_3_qs_4 {

    // This method returns a new array after inserting 'element' at the given 'position'
    public static int[] insertElement(int[] arr, int element, int position) {
        int n = arr.length;
        // Validate the position: allow positions from 0 up to n (inserting at the end)
        if (position < 0 || position > n) {
            System.out.println("Invalid position. Please enter a position between 0 and " + n + ".");
            return arr;  // Alternatively, throw an exception or handle the error as needed
        }

        // Create a new array with one extra slot
        int[] newArray = new int[n + 1];

        // Copy elements before the specified position
        for (int i = 0; i < position; i++) {
            newArray[i] = arr[i];
        }

        // Insert the new element at the specified position
        newArray[position] = element;

        // Copy the remaining elements from the original array
        for (int i = position; i < n; i++) {
            newArray[i + 1] = arr[i];
        }

        return newArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements for the initial array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Create and populate the original array
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Read the element to insert and its target position
        System.out.print("Enter the element to insert: ");
        int element = scanner.nextInt();
        System.out.print("Enter the position where you want to insert the element (0-indexed): ");
        int position = scanner.nextInt();

        // Insert the element and get the updated array
        int[] modifiedArray = insertElement(arr, element, position);

        // Display the modified array
        System.out.println("Array after insertion: " + Arrays.toString(modifiedArray));

        scanner.close();
    }
}
