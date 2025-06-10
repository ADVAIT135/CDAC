// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 6 -  Question -  9

public class Employee {
    // Private instance variables
    private int employee_id;
    private String employee_name;
    private double employee_salary;

    // Constructor to initialize employee_id, employee_name, and employee_salary
    public Employee(int employee_id, String employee_name, double employee_salary) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
    }

    // Getter and setter for employee_id
    public int getEmployeeId() {
        return employee_id;
    }

    public void setEmployeeId(int employee_id) {
        this.employee_id = employee_id;
    }

    // Getter and setter for employee_name
    public String getEmployeeName() {
        return employee_name;
    }

    public void setEmployeeName(String employee_name) {
        this.employee_name = employee_name;
    }

    // Getter for employee_salary that returns a formatted string.
    public String getEmployeeSalary() {
        return String.format("$%.2f", employee_salary);
    }

    // Main method to demonstrate usage of the Employee class
    public static void main(String[] args) {
        // Create an employee object with id, name, and salary
        Employee emp = new Employee(303, "Advait Chavan", 75000.0);

        // Display initial employee details
        System.out.println("Employee ID: " + emp.getEmployeeId());
        System.out.println("Employee Name: " + emp.getEmployeeName());
        System.out.println("Employee Salary: " + emp.getEmployeeSalary());

        // Modify the employee id and name using setters
        emp.setEmployeeId(304);
        emp.setEmployeeName("Vineet Kotian");

        // Display the updated employee details
        System.out.println("\nAfter updating ID and Name:");
        System.out.println("Employee ID: " + emp.getEmployeeId());
        System.out.println("Employee Name: " + emp.getEmployeeName());
        System.out.println("Employee Salary: " + emp.getEmployeeSalary());
    }
}
