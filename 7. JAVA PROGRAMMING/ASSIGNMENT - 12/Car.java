// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  9

public class Car {
    public void startEngine() {
        // Local inner class defined within the method.
        class Engine {
            public void run() {
            	System.out.println("Engine is running");
                System.out.println("Engine is roaring!!!!");
                System.out.println("Let's hit the streets!!!");
                System.out.println("Let's burn the tyre rubber!!!");
            }
        }
        Engine engine = new Engine();
        engine.run();
    }
    
    public static void main(String[] args) {
        Car car = new Car();
        car.startEngine();
    }
}
