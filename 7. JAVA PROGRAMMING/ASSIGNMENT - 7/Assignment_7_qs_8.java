// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  8

public class Assignment_7_qs_8 {
    public static void main(String[] args) {
        String input = "3May2001";
        int specificNumber = 3;
        
        // Convert the number to a string for comparison.
        String prefix = String.valueOf(specificNumber);
        
        if (input.startsWith(prefix)) {
            System.out.println("The string starts with the number " + specificNumber + ".");
        } else {
            System.out.println("The string does not start with the number " + specificNumber + ".");
        }
    }
}
