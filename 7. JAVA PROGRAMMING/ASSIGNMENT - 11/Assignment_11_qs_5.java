// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  5

import java.util.concurrent.*;

public class Assignment_11_qs_5 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () ->
            System.out.println("Scheduled task executed at: " + System.currentTimeMillis());
        
        // Schedule the task to run after a 3-second delay
        scheduler.schedule(task, 3, TimeUnit.SECONDS);

        // Alternatively, schedule at a fixed rate:
        // scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
        
        scheduler.shutdown();
    }
}
