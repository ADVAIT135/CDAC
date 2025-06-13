// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  3


// Define the enum for geometric shapes
enum Shape {
    CIRCLE, SQUARE, TRIANGLE, RECTANGLE;
}

// Test class to print each shape
public class Assignment_10_qs_3 {
    public static void main(String[] args) {
        for (Shape shape : Shape.values()) {
            System.out.println(shape);
        }
    }
}
