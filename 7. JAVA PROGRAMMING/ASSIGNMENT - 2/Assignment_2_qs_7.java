// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 2 - Question -  7

public class Assignment_2_qs_7 {
    public static void main(String[] args) {
        // Retrieve the Java version from system properties
        String javaVersion = System.getProperty("java.version");

        // In a running Java application, this property should always have a non-empty value.
        if (javaVersion != null && !javaVersion.isEmpty()) {
            System.out.println("Java is installed on this system.");
            System.out.println("Java version: " + javaVersion);
        } else {
            System.out.println("Java does not appear to be installed.");
        }
    }
}
