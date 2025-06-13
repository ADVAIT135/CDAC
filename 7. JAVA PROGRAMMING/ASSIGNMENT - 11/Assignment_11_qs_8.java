// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  8

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Assignment_11_qs_8 {
    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        
        // Populate the queue
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        
        // Define a task for polling elements
        Runnable pollTask = () -> {
            Integer item;
            while ((item = queue.poll()) != null) {
                System.out.println(Thread.currentThread().getName() + " polled: " + item);
            }
        };

        Thread t1 = new Thread(pollTask, "Thread-1");
        Thread t2 = new Thread(pollTask, "Thread-2");
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
