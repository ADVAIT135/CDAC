import java.util.Scanner;

public class JAVA_PROGRAMMING_ASSIGNMENT_QS_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int COUNT = 5;
        double sum = 0;

        System.out.println("Enter " + COUNT + " numbers:");

        for (int i = 1; i <= COUNT; i++) {
            System.out.print("Number " + i + ": ");
            
          
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Please enter a numeric value: ");
                scanner.next();  
            }
            
            double num = scanner.nextDouble();
            sum += num;
        }
        
        scanner.close();

        double average = sum / COUNT;

        System.out.println("\nSum: " + sum);
        System.out.println("Average: " + average);
    }
}