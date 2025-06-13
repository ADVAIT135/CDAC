// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  2

// Define the enum for cardinal directions
enum Direction {
    NORTH, SOUTH, EAST, WEST;
}

// Test class to print all directions
public class Assignment_10_qs_2 {
    public static void main(String[] args) {
        for (Direction d : Direction.values()) {
            System.out.println(d);
        }
    }
}
