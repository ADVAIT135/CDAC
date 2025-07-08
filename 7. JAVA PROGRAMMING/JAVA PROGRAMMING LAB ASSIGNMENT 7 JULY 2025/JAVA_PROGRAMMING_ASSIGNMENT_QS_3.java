import java.util.Arrays;

public class JAVA_PROGRAMMING_ASSIGNMENT_QS_3 {
    public static void main(String[] args) {
        
        int[] original = {3, 5, 2001, 5, 8, 1974};
        
        
        int[] swapped = Arrays.copyOf(original, original.length);
        
       
        if (swapped.length > 1) {
            int temp = swapped[0];
            swapped[0] = swapped[swapped.length - 1];
            swapped[swapped.length - 1] = temp;
        }
        
        
        System.out.println("Original array: " + Arrays.toString(original));
        System.out.println("Swapped array : " + Arrays.toString(swapped));
    }
}