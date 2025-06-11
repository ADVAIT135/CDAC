// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  3

// Custom exception for strings without vowels
@SuppressWarnings("serial")
class NoVowelException extends Exception {
    public NoVowelException(String message) {
        super(message);
    }
}

public class Assignment_7_qs_3 {
    
    // Method to check if a string contains vowels
    public static void checkStringForVowels(String input) throws NoVowelException {
        // Regular expression to check for vowels (case insensitive)
        if (!input.toLowerCase().matches(".*[aeiou].*")) {
            throw new NoVowelException("The string '" + input + "' does not contain any vowels.");
        }
        System.out.println("The string '" + input + "' contains at least one vowel.");
    }
    
    public static void main(String[] args) {
        String testString1 = "Cdac Noida"; 
        String testString2 = "Advait Chavan";
        String testString3 = "JV prg";
        
        try {
            checkStringForVowels(testString1);
        } catch (NoVowelException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        try {
            checkStringForVowels(testString2);
        } catch (NoVowelException e) {
            System.err.println("Error: " + e.getMessage());
        }
        try {
            checkStringForVowels(testString3);
        } catch (NoVowelException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
