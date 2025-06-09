// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  5

class Shape {
    // Default implementation returns 0.
    double getArea() {
        return 0;
    }
}

// Subclass Rectangle that overrides getArea() to calculate the area of a rectangle.
class Rectangle extends Shape {
    double length;
    double width;

    // Constructor to initialize rectangle dimensions.
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // Overridden getArea() method to compute area.
    @Override
    double getArea() {
        return length * width;
    }
}