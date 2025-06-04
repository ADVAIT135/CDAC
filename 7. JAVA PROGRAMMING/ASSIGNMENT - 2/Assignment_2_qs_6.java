// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 2 - Question -  6

public class Assignment_2_qs_6 {
    public static void main(String[] args) {
        // Initialize variables
        int a = 5;
        int b = 10;

        // Display values before swapping
        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Swap logic using a temporary variable
        int temp = a;  // Preserve the value of a in temp
        a = b;         // Assign b to a
        b = temp;      // Now assign the value stored in temp (the original a) to b

        // Display values after swapping
        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
