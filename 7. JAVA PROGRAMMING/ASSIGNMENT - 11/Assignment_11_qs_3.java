// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  3

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assignment_11_qs_3 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        // Initial entries
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable task1 = () -> map.compute("A", (k, v) -> v + 10);
        Runnable task2 = () -> map.computeIfPresent("B", (k, v) -> v * 2);
        Runnable task3 = () -> map.putIfAbsent("D", 4);

        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);
        executor.shutdown();
        while (!executor.isTerminated()) { }
        System.out.println("Updated Map: " + map);
    }
}
