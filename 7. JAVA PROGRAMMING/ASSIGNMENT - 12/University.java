// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  6

public class University {
    static class Department {
        private String departmentName;
        private int facultyCount;
        
        public Department(String departmentName, int facultyCount) {
            this.departmentName = departmentName;
            this.facultyCount = facultyCount;
        }
        
        public void displayInfo() {
            System.out.println("Department: " + departmentName + ", Faculty Count: " + facultyCount);
        }
    }
    
    public static void main(String[] args) {
        Department csDept = new Department("Computer Science", 50);
        csDept.displayInfo();
    }
}
