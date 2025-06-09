// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  3

class Vehicle {
    public void drive() {
        System.out.println("Driving vehicle");
    }
}

// Subclass Car that overrides the drive() method
class Car extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Repairing a car");
    }
}
