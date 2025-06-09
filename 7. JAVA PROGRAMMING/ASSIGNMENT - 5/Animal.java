// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  1

// Animal.java
public class Animal {
    // Method that makes a generic sound
    public void makeSound() {
        System.out.println("Generic animal sound");
    }
  
    // Main method to test the functionality
    public static void main(String[] args) {
        // Creating an instance of Animal and calling its method
        Animal animal = new Animal();
        animal.makeSound();  // Output: Generic animal sound
        
        // Creating an instance of Cat and calling its overridden method
        Animal cat = new Cat();
        cat.makeSound();     // Output: Bark
    }
}

// Cat.java
class Cat extends Animal {
    // Override the makeSound method to print "Bark"
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}
