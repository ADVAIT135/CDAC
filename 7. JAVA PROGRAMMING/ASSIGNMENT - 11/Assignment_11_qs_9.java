// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  9

import java.util.LinkedList;
import java.util.Queue;

public class Assignment_11_qs_9 {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5;

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (queue.size() == LIMIT) {
                    wait();
                }
                System.out.println("Produced: " + value);
                queue.offer(value++);
                notify(); // Notify the consumer that an item is available
            }
            Thread.sleep(500); // Simulate time for production
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait();
                }
                int val = queue.poll();
                System.out.println("Consumed: " + val);
                notify(); // Notify the producer that space is available
            }
            Thread.sleep(700); // Simulate time for consumption
        }
    }

    public static void main(String[] args) {
    	Assignment_11_qs_9 pc = new Assignment_11_qs_9();
        Thread producerThread = new Thread(() -> {
            try { pc.produce(); } catch (InterruptedException e) { e.printStackTrace(); }
        });
        Thread consumerThread = new Thread(() -> {
            try { pc.consume(); } catch (InterruptedException e) { e.printStackTrace(); }
        });
        producerThread.start();
        consumerThread.start();
    }
}
