// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  6

public class TrafficLight {
    // Attributes: current color of the light and how long it stays on (in seconds)
    private String color;
    private int duration;

    // Constructor to initialize the TrafficLight with a color and duration
    public TrafficLight(String color, int duration) {
        this.color = color;
        this.duration = duration;
    }

    // Method to change the color of the traffic light
    public void changeColor(String newColor) {
        this.color = newColor;
    }

    // Method to check if the current traffic light is red
    public boolean isRed() {
        return "red".equalsIgnoreCase(color);
    }

    // Method to check if the current traffic light is green
    public boolean isGreen() {
        return "green".equalsIgnoreCase(color);
    }

    // Getter method for the color
    public String getColor() {
        return color;
    }

    // Getter method for the duration
    public int getDuration() {
        return duration;
    }

    // Main method to test the TrafficLight class functionality
    public static void main(String[] args) {
        // Create a TrafficLight object with initial color "red" and duration 30 seconds
        TrafficLight light = new TrafficLight("red", 30);
        System.out.println("Initial Traffic Light State: ");
        System.out.println("Color: " + light.getColor() + ", Duration: " + light.getDuration() + " seconds");
        System.out.println("Is the light red? " + light.isRed());
        System.out.println("Is the light green? " + light.isGreen());

        // Change the light's color to green and display the new state
        light.changeColor("green");
        System.out.println("\nAfter changing the color:");
        System.out.println("Color: " + light.getColor() + ", Duration: " + light.getDuration() + " seconds");
        System.out.println("Is the light red? " + light.isRed());
        System.out.println("Is the light green? " + light.isGreen());
    }
}
