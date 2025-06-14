// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  1

public class Assignment_12_qs_1 {
    public static <T> boolean areArraysEqual(T[] a, T[] b) {
        if (a == null || b == null)
            return false;
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) { // Uses T's equals() method.
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};
        Integer[] arr3 = {1, 2, 4};

        System.out.println("arr1 and arr2 equal? " + areArraysEqual(arr1, arr2)); // Expected: true
        System.out.println("arr1 and arr3 equal? " + areArraysEqual(arr1, arr3)); // Expected: false
    }
}
