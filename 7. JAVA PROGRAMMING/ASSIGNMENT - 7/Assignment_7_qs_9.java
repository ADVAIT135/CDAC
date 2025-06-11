// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 7 -  Question -  9

public class Assignment_7_qs_9 {
    
    public static String removeLeadingZeros(String ip) {
        String[] parts = ip.split("\\.");
        StringBuilder formattedIP = new StringBuilder();
        
        for (int i = 0; i < parts.length; i++) {
            // Parsing to int removes any leading zeros.
            int num = Integer.parseInt(parts[i]);
            formattedIP.append(num);
            if (i < parts.length - 1) {
                formattedIP.append(".");
            }
        }
        return formattedIP.toString();
    }
    
    public static void main(String[] args) {
        String ip = "001.010.000.255";
        System.out.println("Original IP: " + ip);
        System.out.println("Formatted IP: " + removeLeadingZeros(ip));
    }
}
