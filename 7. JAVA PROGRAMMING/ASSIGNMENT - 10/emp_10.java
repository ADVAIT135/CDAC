// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  5

// Employee.java
public class emp_10 {
    // Attributes of the Employee
    private String name;
    private String jobTitle;
    private double salary; // You can consider this the base salary

    // Constructor to initialize the fields
    public emp_10(String name, String jobTitle, double salary) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

   
    public double calculateSalary() {
        return salary;
    }

    // Method to update the salary with a new value
    public void updateSalary(double newSalary) {
        this.salary = newSalary;
    }

   
    public void increaseSalary(double percentage) {
        if (percentage > 0) {
            this.salary += this.salary * (percentage / 100);
        }
    }

    // Override the toString method to display Employee details cleanly
    @Override
    public String toString() {
        return "Employee{" +
               "name='" + name + '\'' +
               ", jobTitle='" + jobTitle + '\'' +
               ", salary=" + salary +
               '}';
    }

    // Main method to test the Employee class functionality
    public static void main(String[] args) {
        // Creating an Employee object
    	emp_10 emp = new emp_10("ADVAIT CHAVAN", "Software Engineer", 75000.0);
        System.out.println("Initial Employee Details: " + emp);

        // Calculating the salary (here, simply retrieving the base salary)
        double currentSalary = emp.calculateSalary();
        System.out.println("Calculated Salary: " + currentSalary);

        // Updating the salary to a new value
        emp.updateSalary(80000.0);
        System.out.println("Employee Details after salary update: " + emp);

        // Increasing the salary by 10%
        emp.increaseSalary(10);
        System.out.println("Employee Details after a 10% raise: " + emp);
    }
}
