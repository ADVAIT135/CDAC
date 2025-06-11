// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  5

import java.util.function.BiFunction;

public class Assignment_8_qs_5 {
    public static void main(String[] args) {
        // Lambda expression to sum two integers
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        
        int result = sum.apply(10, 20);
        System.out.println("Sum: " + result);
    }
}
