// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 12 -  Question -  8

public class Person_10{
    // Inner class Address
    public class Address {
        private String city;
        private String state;
        
        public Address(String city, String state) {
            this.city = city;
            this.state = state;
        }
        
        public void displayAddress() {
            System.out.println("City: " + city + ", State: " + state);
        }
    }
    
    public static void main(String[] args) {
        Person_10 person = new Person_10();
        Address address = person.new Address("MALVAN", "Maharashtra");
        address.displayAddress();
    }
}
