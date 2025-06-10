// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 6 -  Question -  8

public class Point {
    // Instance variables for the coordinates
    private double x;
    private double y;

    // Overloaded constructor that takes int parameters
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Overloaded constructor that takes double parameters
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // toString method for a readable representation of the point
    @Override
    public String toString() {
        return "Point [x = " + x + ", y = " + y + "]";
    }

    // Main method to test the overloaded constructors
    public static void main(String[] args) {
        // Creating a Point object using the int constructor
        Point intPoint = new Point(3, 4);
        System.out.println("Point created using int parameters: " + intPoint);

        // Creating a Point object using the double constructor
        Point doublePoint = new Point(3.5, 4.5);
        System.out.println("Point created using double parameters: " + doublePoint);
    }
}
