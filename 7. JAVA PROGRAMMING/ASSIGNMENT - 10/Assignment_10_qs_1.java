// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  1


// Define the enum for weekend days
enum Weekend {
    SATURDAY, SUNDAY;
}

// Test class to print each weekend day
public class Assignment_10_qs_1 {
    public static void main(String[] args) {
        for (Weekend day : Weekend.values()) {
            System.out.println(day);
        }
    }
}
