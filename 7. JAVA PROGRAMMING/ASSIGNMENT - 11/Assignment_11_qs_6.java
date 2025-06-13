// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  6

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class Assignment_11_qs_6 extends RecursiveTask<Long> {
    private long[] nums;
    private int start, end;
    private static final int THRESHOLD = 20;

    public Assignment_11_qs_6(long[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += nums[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            Assignment_11_qs_6 left = new Assignment_11_qs_6(nums, start, mid);
            Assignment_11_qs_6 right = new Assignment_11_qs_6(nums, mid, end);
            left.fork();
            long rightResult = right.compute();
            long leftResult = left.join();
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        long[] nums = new long[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        ForkJoinPool pool = new ForkJoinPool();
        Assignment_11_qs_6 task = new Assignment_11_qs_6(nums, 0, nums.length);
        long result = pool.invoke(task);
        System.out.println("Sum: " + result);  // Expected 5050 for numbers 1 to 100
        pool.shutdown();
    }
}
