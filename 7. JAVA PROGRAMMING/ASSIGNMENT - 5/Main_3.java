// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  3


// Main class to run the program
public class Main_3 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle(); // Using the base class
        vehicle.drive();                // Output: Driving vehicle

        Car car = new Car();             // Using the subclass that overrides drive()
        car.drive();                    // Output: Repairing a car
    }
}