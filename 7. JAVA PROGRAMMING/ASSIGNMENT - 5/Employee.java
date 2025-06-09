// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  2


// Employee.java
public class Employee extends Person {
    private String employeeId;
    private String jobTitle;

    // Constructor to initialize first name, last name, employeeId and job title
    public Employee(String firstName, String lastName, String employeeId, String jobTitle) {
        super(firstName, lastName);
        this.employeeId = employeeId;
        this.jobTitle   = jobTitle;
    }

    // New method that returns the employee's ID
    public String getEmployeeId() {
        return employeeId;
    }

    // Overrides the getLastName() method to include the employee's job title
    @Override
    public String getLastName() {
        // Append the job title in parentheses after the last name.
        return super.getLastName() + " (" + jobTitle + ")";
    }
}
