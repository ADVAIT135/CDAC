// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  7

import java.util.function.Predicate;

public class Assignment_8_qs_7 {
    public static void main(String[] args) {
        // Lambda expression to check for perfect square
        Predicate<Integer> isPerfectSquare = n -> {
            if (n < 0) return false;  // negative numbers are not perfect squares
            double sqrt = Math.sqrt(n);
            return sqrt == Math.floor(sqrt);
        };
        
        int number = 230;
        System.out.println("Is " + number + " a perfect square? " + isPerfectSquare.test(number));
    }
}
