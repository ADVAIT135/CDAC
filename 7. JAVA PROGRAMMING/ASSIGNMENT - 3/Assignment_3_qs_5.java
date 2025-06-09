// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  5


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Assignment_3_qs_5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements in the array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Populate the array with user inputs
        int[] arr = new int[n];
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Sets to hold unique values and duplicate values
        Set<Integer> uniqueValues = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        // Iterate through each element in the array
        for (int value : arr) {
            // If the value is already in uniqueValues, add to duplicates
            // Otherwise, add the value to uniqueValues
            if (!uniqueValues.add(value)) {
                duplicates.add(value);
            }
        }

        // Display the duplicates found in the array
        if (duplicates.isEmpty()) {
            System.out.println("No duplicate elements found in the array.");
        } else {
            System.out.println("Duplicate values in the array: " + duplicates);
        }

        scanner.close();
    }
}
