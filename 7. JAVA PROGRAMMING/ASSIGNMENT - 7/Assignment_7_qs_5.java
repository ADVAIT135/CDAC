// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  5

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Custom exception to signal that a duplicate number was found
@SuppressWarnings("serial")
class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String message) {
        super(message);
    }
}

public class Assignment_7_qs_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the number of integers: ");
            int count = scanner.nextInt();

            Set<Integer> uniqueNumbers = new HashSet<>();

            System.out.println("Enter " + count + " integers:");
            for (int i = 0; i < count; i++) {
                int number = scanner.nextInt();
                if (uniqueNumbers.contains(number)) {
                    throw new DuplicateNumberException("Duplicate number found: " + number);
                }
                uniqueNumbers.add(number);
            }
            System.out.println("All numbers entered are unique.");
        } catch (DuplicateNumberException e) {
            System.err.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Input error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
