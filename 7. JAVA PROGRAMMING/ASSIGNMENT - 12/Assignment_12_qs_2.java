// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  2

import java.util.*;

public class Assignment_12_qs_2 {
    public static Map<String, Integer> sumEvenOdd(List<? extends Number> numbers) {
        int evenSum = 0;
        int oddSum  = 0;
        for (Number num : numbers) {
            int value = num.intValue();
            if (value % 2 == 0) {
                evenSum += value;
            } else {
                oddSum += value;
            }
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("even", evenSum);
        result.put("odd", oddSum);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<String, Integer> result = sumEvenOdd(nums);
        System.out.println("Even Sum: " + result.get("even") + ", Odd Sum: " + result.get("odd"));
    }
}
