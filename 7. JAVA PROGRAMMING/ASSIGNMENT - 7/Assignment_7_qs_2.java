// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  2

import java.io.*;

// Custom exception for an empty file
@SuppressWarnings("serial")
class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}

public class Assignment_7_qs_2 {
    
    // Method that checks if the file is empty and reads it if not
    public static void checkEmptyFile(String filePath) throws EmptyFileException, IOException {
        File file = new File(filePath);
        
        // Check if the file is empty using its length
        if (file.length() == 0) {
            throw new EmptyFileException("The file '" + filePath + "' is empty.");
        }
        
        // If not empty, read and print the content
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
    
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Advait\\Desktop\\CDAC\\ASSIGNMENTS -- SOLUTIONS\\"
        		+ "MODULE 7 -- JAVA PROGRAMMING\\ASSIGNMENT 7\\src\\test.txt"; 
        
        try {
            checkEmptyFile(filePath);
        } catch (EmptyFileException e) {
            System.err.println("EmptyFileException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
