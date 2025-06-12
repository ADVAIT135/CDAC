// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 9 -  Question -  3

import java.lang.reflect.Method;

class Example {
    // A simple method to be invoked
    public void greet(String message) {
        System.out.println("Greeting: " + message);
    }
}

public class Assignment_9_qs_3 {
    public static void main(String[] args) {
        try {
            // Step 1: Create an instance of Example
            Example exampleObj = new Example();

            // Step 2: Get the Class object associated with Example
            Class<?> clazz = exampleObj.getClass();

            // Step 3: Retrieve the Method object for 'greet' that accepts a String parameter
            Method greetMethod = clazz.getDeclaredMethod("greet", String.class);


            // Step 4: Invoke the method on the exampleObj instance with the desired argument
            greetMethod.invoke(exampleObj, "Hello, Advait!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
