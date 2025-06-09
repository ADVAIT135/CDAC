// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  6

class Animal_2{
    // The move() method for a generic animal.
    void move() {
        System.out.println("The animal moves.");
    }
}

// Subclass called Cheetah that overrides the move() method
class Cheetah extends Animal_2{
    @Override
    void move() {
        System.out.println("The cheetah runs swiftly!");
    }
}
