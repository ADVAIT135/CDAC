// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 9 -  Question -  2

import java.lang.reflect.Field;

class Person {
    private String name = "Advait Chavan";

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}

public class Assignment_9_qs_2 {
    public static void main(String[] args) {
        try {
            // Create an instance of Person
            Person person = new Person();
            System.out.println("Before modification: " + person);

            // Obtain the Class object of Person
            Class<?> personClass = person.getClass();

            // Get the private field 'name'
            Field nameField = personClass.getDeclaredField("name");

            // Allow access to the private field
            nameField.setAccessible(true);

            // Modify the private field's value
            nameField.set(person, "Gurunath Chavan");

            System.out.println("After modification: " + person);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
