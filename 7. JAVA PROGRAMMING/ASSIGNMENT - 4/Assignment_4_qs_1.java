// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 4 -  Question -  1

public class Assignment_4_qs_1 {

    // Method to compute the sum of digits of an integer
    public static int sumOfDigits(int number) {
        // Handle negative numbers by converting to positive
        number = Math.abs(number);
        int sum = 0;
        
        // Continue until all digits are processed
        while (number > 0) {
            sum += number % 10;  // Add the last digit to the sum
            number /= 10;        // Remove the last digit
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        int number = 30521;
        System.out.println("The sum of digits in " + number + " is: " + sumOfDigits(number));
    }
}
