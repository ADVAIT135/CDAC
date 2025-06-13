// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  7

import java.util.concurrent.*;

public class Assignment_11_qs_7 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<String> task = () -> {
            Thread.sleep(2000);  // Simulate a time-consuming computation
            return "Task's execution result";
        };

        Future<String> future = executor.submit(task);
        System.out.println("Task submitted ...");
        try {
            // Blocks until the task completes and returns the result
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
