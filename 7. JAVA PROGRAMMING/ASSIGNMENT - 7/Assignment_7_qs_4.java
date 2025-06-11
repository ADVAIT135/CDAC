// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  4

import java.io.*;

public class Assignment_7_qs_4 {
    
    // Method that reads a file and throws an exception if the file is not found
    public static void readFile(String filePath) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        
        // Check if the file exists before trying to read it
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        
        // Read and display the file content
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
            readFile(filePath);
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
