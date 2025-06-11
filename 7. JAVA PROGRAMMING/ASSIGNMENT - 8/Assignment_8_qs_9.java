// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  9

import java.util.Arrays;
import java.util.List;

public class Assignment_8_qs_9 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Sum of squares of odd numbers
        int sumOddSquares = numbers.stream()
                                   .filter(n -> n % 2 != 0)
                                   .map(n -> n * n)
                                   .reduce(0, Integer::sum);
        
        // Sum of squares of even numbers
        int sumEvenSquares = numbers.stream()
                                    .filter(n -> n % 2 == 0)
                                    .map(n -> n * n)
                                    .reduce(0, Integer::sum);
        
        System.out.println("Sum of squares of odd numbers: " + sumOddSquares);
        System.out.println("Sum of squares of even numbers: " + sumEvenSquares);
    }
}

