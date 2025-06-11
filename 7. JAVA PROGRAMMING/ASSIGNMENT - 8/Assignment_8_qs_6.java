// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  6

import java.util.Arrays;
import java.util.List;

public class Assignment_8_qs_6 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(03, 05, 2001, 29, 01, 1970);
        
        // Using lambda expressions with max and min
        int max = numbers.stream().max((a, b) -> a - b).get();
        int min = numbers.stream().min((a, b) -> a - b).get();
        
        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
    }
}
