// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  5

import java.util.Scanner;

public class Assignment_4_qs_5  {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console.
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // First, print "Hello" on one line.
        System.out.println("Hello");
        // Then, print the user's name on a separate line.
        System.out.println(name);

        // Close the Scanner to free up resources.
        scanner.close();
    }
}
