// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  4

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment_8_qs_4 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Advait", "Cdac", "Chavan", "Noida");
        
        // Convert to uppercase
        List<String> uppercaseWords = words.stream()
                                             .map(word -> word.toUpperCase())
                                             .collect(Collectors.toList());
        
        // Convert to lowercase
        List<String> lowercaseWords = words.stream()
                                             .map(word -> word.toLowerCase())
                                             .collect(Collectors.toList());
                                             
        System.out.println("Uppercase: " + uppercaseWords);
        System.out.println("Lowercase: " + lowercaseWords);
    }
}
