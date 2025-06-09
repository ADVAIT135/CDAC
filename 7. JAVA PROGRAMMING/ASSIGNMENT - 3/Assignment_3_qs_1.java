// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  1

import java.util.Arrays;

public class Assignment_3_qs_1 {
    public static void main(String[] args) {
        // Sorting a numeric array (integers)
        int[] numbers = {03, 05, 01, 29, 70};
        System.out.println("Numeric array before sort: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("Numeric array after sort: " + Arrays.toString(numbers));

        // Sorting a string array (alphabetical order)
        String[] fruits = {"Advait", "Chavan", "CDAC", "PGDAI"};
        System.out.println("String array before sort: " + Arrays.toString(fruits));
        Arrays.sort(fruits);
        System.out.println("String array after sort: " + Arrays.toString(fruits));
    }
}
