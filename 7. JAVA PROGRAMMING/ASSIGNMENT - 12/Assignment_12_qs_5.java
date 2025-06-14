// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  5

import java.util.*;

public class Assignment_12_qs_5 {
    public static <T> List<T> mergeAlternate(List<T> list1, List<T> list2) {
        List<T> merged = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int minSize = Math.min(size1, size2);
        
        // Alternate elements from both lists.
        for (int i = 0; i < minSize; i++) {
            merged.add(list1.get(i));
            merged.add(list2.get(i));
        }
        
        // Append the remaining elements from the longer list.
        if (size1 > minSize) {
            merged.addAll(list1.subList(minSize, size1));
        }
        if (size2 > minSize) {
            merged.addAll(list2.subList(minSize, size2));
        }
        return merged;
    }

    public static void main(String[] args) {
        List<String> letters = Arrays.asList("A", "B", "C");
        List<String> numbers = Arrays.asList("1", "2", "3", "4");
        List<String> mergedList = mergeAlternate(letters, numbers);
        System.out.println("Merged list: " + mergedList);
    }
}
