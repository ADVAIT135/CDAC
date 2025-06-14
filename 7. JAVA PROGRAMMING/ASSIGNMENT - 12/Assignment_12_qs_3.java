// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  3

import java.util.*;

public class Assignment_12_qs_3 {
    public static <T> List<T> reverseList(List<T> list) {
        List<T> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        return reversed;
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Advait", "Gurunath", "Chavan"));
        List<String> reversedNames = reverseList(names);
        System.out.println("Original list: " + names);
        System.out.println("Reversed list: " + reversedNames);
    }
}
