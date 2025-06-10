// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 6 -  Question -  1

public class Assignment_6_qs_1 {
    public static void main(String[] args) {
        int[] numbers = { 03, 05, 20, 29, 74 };
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double average = (double) sum / numbers.length;
        System.out.println("Average: " + average);
    }
}
