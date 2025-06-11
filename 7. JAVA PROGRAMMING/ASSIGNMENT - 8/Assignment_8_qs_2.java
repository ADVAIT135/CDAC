// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  2

import java.util.function.BiFunction;

public class Assignment_8_qs_2  {
    public static void main(String[] args) {
        // Lambda expression to concatenate two strings
        BiFunction<String, String, String> concatenator = (str1, str2) -> str1 + str2;
        
        String result = concatenator.apply("Advait", "Chavan");
        System.out.println("Concatenated String: " + result);
    }
}

