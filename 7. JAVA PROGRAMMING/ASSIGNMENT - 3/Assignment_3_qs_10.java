// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 3 -  Question -  10

public class Assignment_3_qs_10 {
    public static char findMissingLetter(char[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] != arr[i] + 1) {
                return (char) (arr[i] + 1);
            }
        }
        return '?'; // Fallback (shouldn't occur if exactly one letter is missing)
    }

    public static void main(String[] args) {
        char[] letters = {'a', 'b', 'c', 'd', 'e'};
        System.out.println("Missing letter: " + findMissingLetter(letters));
    }
}
