// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 6 -  Question -  6

public class Book {
    // Attributes of the Book class
    private String title;
    private String author;
    private double price;

    // Constructor to initialize title, author, and price when creating a Book object
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter method for the title attribute
    public String getTitle() {
        return title;
    }

    // Getter method for the author attribute
    public String getAuthor() {
        return author;
    }

    // Getter method for the price attribute
    public double getPrice() {
        return price;
    }

    // Overriding toString() method for a user-friendly representation of the Book object
    @Override
    public String toString() {
        return "Book [Title: " + title + ", Author: " + author + ", Price: $" + price + "]";
    }

    // Main method to test the Book class
    public static void main(String[] args) {
        // Creating two Book objects using the defined constructor
    	Book book1 = new Book("Hands-On Machine Learning with Scikit-Learn, Keras, and TensorFlow", 
                "Aurélien Géron", 40.50);
    	Book book2 = new Book("Machine Learning for Absolute Beginners", 
                "Oliver Theobald", 15.00);
    	Book book3 = new Book("Designing Machine Learning Systems", 
                "Chip Huyen", 35.75);

        // Printing the details of each book using the overridden toString() method
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);
    }
}
