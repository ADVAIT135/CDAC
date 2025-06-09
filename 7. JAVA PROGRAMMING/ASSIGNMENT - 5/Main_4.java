// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 5 -  Question -  4

public class Main_4{
    public static void main(String[] args) {
        // Using the base Employee functionalities
        Employee_4 emp = new Employee_4();
        emp.work();
        System.out.println("Employee Salary: " + emp.getSalary());

        // Using the HRManager functionalities
        HRManager hrManager = new HRManager();
        hrManager.work();
        hrManager.addEmployee("Advait Chavan");
        System.out.println("ML Ops Manager Salary: " + hrManager.getSalary());
    }
}