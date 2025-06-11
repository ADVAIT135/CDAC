// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  10

import java.util.Arrays;
import java.util.List;

public class Assignment_8_qs_10 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Advait", "Cdac", "Noida", "Artificial", 
        		"Intelligence", "Chavan");
        String specificWord = "chavan";
        
        // Lambda expression to check if the list contains the specific word (ignoring case)
        boolean contains = words.stream()
                                .anyMatch(word -> word.equalsIgnoreCase(specificWord));
        
        System.out.println("List contains '" + specificWord + "': " + contains);
    }
}
