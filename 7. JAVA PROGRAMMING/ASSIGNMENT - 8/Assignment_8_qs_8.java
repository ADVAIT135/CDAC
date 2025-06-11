// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 8 -  Question -  8


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment_8_qs_8 {
    public static void main(String[] args) {
        Integer[] array = {5, 3, 9, 1, 7, 3, 9};
        
        // Remove duplicates, sort the array
        List<Integer> distinctSorted = Arrays.stream(array)
                                               .distinct()
                                               .sorted()
                                               .collect(Collectors.toList());
        
        if (distinctSorted.size() < 2) {
            System.out.println("Not enough distinct elements to find second largest and smallest.");
        } else {
            int secondSmallest = distinctSorted.get(1);
            int secondLargest = distinctSorted.get(distinctSorted.size() - 2);
            System.out.println("Second Smallest: " + secondSmallest);
            System.out.println("Second Largest: " + secondLargest);
        }
    }
}
