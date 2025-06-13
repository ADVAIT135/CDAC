// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  2

import java.util.concurrent.*;

public class Assignment_11_qs_2 {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int limit = 10000;
        int numThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Future<Long>[] futures = new Future[numThreads];
        int range = limit / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int start = i * range + 1;
            int end = (i == numThreads - 1) ? limit : (i + 1) * range;
            futures[i] = executor.submit(() -> {
                long sum = 0;
                for (int n = start; n <= end; n++) {
                    if (isPrime(n)) {
                        sum += n;
                    }
                }
                return sum;
            });
        }

        long totalSum = 0;
        for (Future<Long> f : futures) {
            totalSum += f.get();
        }
        executor.shutdown();
        System.out.println("Sum of primes up to " + limit + " = " + totalSum);
    }
}
