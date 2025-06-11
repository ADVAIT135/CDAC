// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  7

public class Assignment_7_qs_7 {
    
    public static String reverseWords(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            // If the word length is greater than 3, reverse it.
            if (word.length() > 3) {
                String reversed = new StringBuilder(word).reverse().toString();
                result.append(reversed);
            } else {
                result.append(word);
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
    
    public static void main(String[] args) {
        String input = "This program reverses words longer than three";
        System.out.println("Original: " + input);
        System.out.println("Modified: " + reverseWords(input));
    }
}
