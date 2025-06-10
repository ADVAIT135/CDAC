// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 6 -  Question -  7

// Main class to demonstrate the functionality
public class ShapeDemo {
    public static void main(String[] args) {
        // Create a Circle object with a radius of 5.0
        Circle circle = new Circle(5.0);

        // Display the circle's details: radius, area, and perimeter (circumference)
        System.out.println("Circle with radius " + circle.getRadius() + ":");
        System.out.println("Area: " + circle.getArea());
        System.out.println("Perimeter (Circumference): " + circle.getPerimeter());
    }
}
