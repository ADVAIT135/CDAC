// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  8

public class Rectangle_10 {
    // Attributes for the rectangle's width and height
    private double width;
    private double height;

    // Constructor to initialize the rectangle with specific width and height
    public Rectangle_10(double width, double height) {
        // Optionally, you can add validation here to ensure positive values
        this.width = width;
        this.height = height;
    }

    // Method to calculate the area of the rectangle
    public double calculateArea() {
        return width * height;
    }

    // Method to calculate the perimeter of the rectangle
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    // Main method to test the functionality of the Rectangle class
    public static void main(String[] args) {
        // Creating a Rectangle object with a width of 5.0 and a height of 3.0
        Rectangle_10 rect = new Rectangle_10(5.0, 3.0);

        // Displaying the rectangle's dimensions
        System.out.println("Width: " + rect.width);
        System.out.println("Height: " + rect.height);

        // Calculating and displaying the area and the perimeter
        System.out.println("Area of Rectangle: " + rect.calculateArea());
        System.out.println("Perimeter of Rectangle: " + rect.calculatePerimeter());
    }
}
