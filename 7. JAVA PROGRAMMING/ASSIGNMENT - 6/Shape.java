// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 6 -  Question -  7

// Base class Shape with default implementations for getPerimeter() and getArea()
class Shape {
    // Returns 0.0 by default. Subclasses should override this method.
    public double getPerimeter() {
        return 0.0;
    }

    // Returns 0.0 by default. Subclasses should override this method.
    public double getArea() {
        return 0.0;
    }
}

// Subclass Circle that extends Shape
class Circle extends Shape {
    private double radius;

    // Constructor to initialize the circle with a specific radius
    public Circle(double radius) {
        this.radius = radius;
    }

    // Getter method for the radius (optional, could be useful for further operations)
    public double getRadius() {
        return radius;
    }

    // Overriding getArea() to return the area of the circle
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Overriding getPerimeter() to return the circumference of the circle
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}
