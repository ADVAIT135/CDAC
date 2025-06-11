// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  11

public class Assignment_7_qs_11 {
    
    public static void separate(String input) {
        StringBuilder vowels = new StringBuilder();
        StringBuilder consonants = new StringBuilder();
        String vowelChars = "aeiouAEIOU";
        
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            // Process letters only
            if (Character.isLetter(ch)) {
                if (vowelChars.indexOf(ch) != -1) {
                    vowels.append(ch);
                } else {
                    consonants.append(ch);
                }
            }
        }
        System.out.println("Vowels: " + vowels.toString());
        System.out.println("Consonants: " + consonants.toString());
    }
    
    public static void main(String[] args) {
        String input = "Advait Gurunath Chavan, Cdac Noida";
        System.out.println("Input string: " + input);
        separate(input);
    }
}
