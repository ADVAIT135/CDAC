// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  6

public class Assignment_7_qs_6 {
    public static void main(String[] args) {
        try {
            System.out.println("About to throw an exception deliberately.");
            // Deliberately throw an exception
            throw new Exception("This is a deliberately thrown exception.");
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
        
        System.out.println("Program continues after handling the exception.");
    }
}
