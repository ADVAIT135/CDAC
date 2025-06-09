// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  2

public class Main {
    public static void main(String[] args) {
        // Create a Person object
        Person person = new Person("Advait", "Chavan");
        System.out.println("Person's First Name: " + person.getFirstName());
        System.out.println("Person's Last Name: " + person.getLastName());

        // Create an Employee object which is a subclass of Person
        Employee employee = new Employee("ADVAIT", "CHAVAN", "03052001", "Software Engineer");
        System.out.println("Employee's First Name: " + employee.getFirstName());
        // The last name now includes the job title due to the override
        System.out.println("Employee's Last Name: " + employee.getLastName());
        System.out.println("Employee ID: " + employee.getEmployeeId());
    }
}