import java.util.LinkedHashMap;

public class JAVA_PROGRAMMING_ASSIGNMENT_QS_1{
    public static void main(String[] args) {
        
        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("ADVAIT", 3);
        linkedMap.put("GURUNATH", 5);
        linkedMap.put("CHAVAN", 2);

        
        System.out.println("Original LinkedHashMap: " + linkedMap);

        
        linkedMap.clear();

        
        System.out.println("LinkedHashMap after clear(): " + linkedMap);
    }
}