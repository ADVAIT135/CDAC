// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  1

// Custom exception to be thrown when an odd number is encountered
class OddNumberException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2677180990309998178L;

	public OddNumberException(String message) {
        super(message);
    }
}

public class Assignment_7_qs_1 {

    // Method that takes an integer and throws an exception if it is odd
    public static void checkEven(int number) throws OddNumberException {
        if (number % 2 != 0) {
            throw new OddNumberException("The number " + number + " is odd.");
        } else {
            System.out.println("The number " + number + " is even.");
        }
    }
    
    public static void main(String[] args) {
        // Example 1: Testing with an odd number
        try {
            checkEven(3);
        } catch (OddNumberException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

        // Example 2: Testing with an even number
        try {
            checkEven(4);
        } catch (OddNumberException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }
    }
}
