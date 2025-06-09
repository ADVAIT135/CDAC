// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  4

class Employee_4 {
    // Method to simulate work being done
    public void work() {
        System.out.println("Employee is working.");
    }
    
    // Method to return a base salary for the employee
    public double getSalary() {
        return 50000.0; // Base salary value
    }
}

// The HRManager class that extends Employee
class HRManager extends Employee_4 {
    // Overriding the work() method to provide HR-specific behavior
    @Override
    public void work() {
        System.out.println("HR Manager is overseeing employee operations.");
    }
    
    // New method specific to HRManager for adding a new employee
    public void addEmployee(String name) {
        System.out.println("Added employee: " + name);
    }
}