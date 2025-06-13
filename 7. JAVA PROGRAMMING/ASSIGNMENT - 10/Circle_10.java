// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  9

public class Circle_10{
    // Private attribute for the radius of the circle
    private double radius;

    // Constructor to initialize the circle with a given radius
    public Circle_10(double radius) {
        this.radius = radius;
    }

    // Getter method to access the radius
    public double getRadius() {
        return radius;
    }

    // Setter method to modify the radius
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            System.out.println("Invalid radius. Radius must be positive.");
        }
    }

    // Method to calculate the area of the circle (π * r²)
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // Method to calculate the circumference of the circle (2 * π * r)
    public double calculateCircumference() {
        return 2 * Math.PI * radius;
    }

    // Main method to test the Circle class functionality
    public static void main(String[] args) {
        // Create a Circle object with an initial radius of 5.0
        Circle_10 circle = new Circle_10(5.0);
        System.out.println("Initial Radius: " + circle.getRadius());
        System.out.println("Area: " + circle.calculateArea());
        System.out.println("Circumference: " + circle.calculateCircumference());

        // Modify the radius using the setter method
        circle.setRadius(7.0);
        System.out.println("\nAfter updating the radius:");
        System.out.println("Updated Radius: " + circle.getRadius());
        System.out.println("New Area: " + circle.calculateArea());
        System.out.println("New Circumference: " + circle.calculateCircumference());
    }
}
