// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 6 -  Question -  10

public class Library {
    // Instance variable to store the library name
    private String libraryName;

    // Constructor to initialize the library name
    public Library(String libraryName) {
        this.libraryName = libraryName;
    }

    // Inner class Book
    public class Book {
        // Method that returns the library name from the outer class
        public String getLibraryName() {
            // Access the outer class's libraryName using Library.this.libraryName
            return Library.this.libraryName;
        }
    }

    // Main method to demonstrate instantiation and method call
    public static void main(String[] args) {
        // Create an instance of Library with a specified name
        Library myLibrary = new Library("New Age Coders and Bankers Library");
        
        // Create an instance of the inner class Book using the outer class instance
        Library.Book myBook = myLibrary.new Book();
        
        // Call the getLibraryName() method and print the returned library name
        System.out.println("The library name is: " + myBook.getLibraryName());
    }
}
