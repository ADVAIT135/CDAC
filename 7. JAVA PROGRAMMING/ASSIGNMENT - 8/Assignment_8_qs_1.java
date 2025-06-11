// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  1

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment_8_qs_1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        
        // Using a lambda expression with stream to remove duplicates
        List<Integer> distinctNumbers = numbers.stream()
                                                 .distinct()
                                                 .collect(Collectors.toList());
                                                 
        System.out.println("Distinct Numbers: " + distinctNumbers);
    }
}
