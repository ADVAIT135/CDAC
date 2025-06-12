/*
 * Name : ADVAIT GURUNATH CHAVAN
 * Email ID : advaitchavan135@gmail.com
 * CDAC NOIDA JAVA PROGRAMMING Assignment 9 - Question 4
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class ex {
    private String name;
    private int age;

    // Public no-argument constructor
    public ex() {
        this.name = "Default";
        this.age = 0;
    }

    // Public parameterized constructor
    public ex(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // For displaying the object state
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class Assignment_9_qs_4 {
    public static void main(String[] args) {
        try {
            // Step 1: Obtain the Class object for Person
            Class<ex> personClass = ex.class;
            
            // Step 2: Access the public no-argument constructor
            Constructor<ex> defaultConstructor = personClass.getConstructor();
            ex defaultPerson = defaultConstructor.newInstance();
            System.out.println("Default Person: " + defaultPerson);
            
            // Step 3: Access the public parameterized constructor with String and int parameters
            Constructor<ex> paramConstructor = personClass.getConstructor(String.class, int.class);
            ex customPerson = paramConstructor.newInstance("Advait", 24);
            System.out.println("Custom Person: " + customPerson);
           
            // Step 4: Access the parameterized constructor via getDeclaredConstructor
            // (Demonstrates how any constructor can be accessed, with setAccessible if needed)
            Constructor<ex> declaredConstructor = personClass.getDeclaredConstructor(String.class, int.class);
            declaredConstructor.setAccessible(true);
            ex declaredPerson = declaredConstructor.newInstance("Rex", 5);
            System.out.println("Declared Person: " + declaredPerson);
            
        } catch (NoSuchMethodException | InstantiationException 
                 | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
