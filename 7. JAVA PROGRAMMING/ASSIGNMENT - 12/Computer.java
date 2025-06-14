// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  7

public class Computer {
    // Non-static inner class
    class Processor {
        private String brand;
        private String speed;
        
        public Processor(String brand, String speed) {
            this.brand = brand;
            this.speed = speed;
        }
        
        public void displayDetails() {
            System.out.println("Processor Brand: " + brand + ", Speed: " + speed);
        }
    }
    
    public static void main(String[] args) {
        Computer computer = new Computer();
        Processor processor = computer.new Processor("Intel", "3.5GHz");
        processor.displayDetails();
    }
}

