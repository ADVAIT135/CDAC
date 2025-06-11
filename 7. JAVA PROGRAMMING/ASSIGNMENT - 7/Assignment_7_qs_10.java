// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  10

public class Assignment_7_qs_10 {
    
    public static boolean isValidPIN(String pin) {
        // Regex that allows for exactly 4, 6 or 8 digits.
        return pin.matches("\\d{4}|\\d{6}|\\d{8}");
    }
    
    public static void main(String[] args) {
        String[] testPins = {"1234", "123456", "12345678", "12345", "12a4"};
        
        for (String pin : testPins) {
            System.out.println("PIN " + pin + " valid? " + isValidPIN(pin));
        }
    }
}
