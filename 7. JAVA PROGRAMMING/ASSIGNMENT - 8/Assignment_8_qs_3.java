// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  3

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class  Assignment_8_qs_3 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Lambda to filter even numbers
        List<Integer> evenNumbers = numbers.stream()
                                             .filter(n -> n % 2 == 0)
                                             .collect(Collectors.toList());
        
        // Lambda to filter odd numbers
        List<Integer> oddNumbers = numbers.stream()
                                            .filter(n -> n % 2 != 0)
                                            .collect(Collectors.toList());
        
        System.out.println("Even Numbers: " + evenNumbers);
        System.out.println("Odd Numbers: " + oddNumbers);
    }
}
