// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  4

import java.util.concurrent.Exchanger;

public class Assignment_11_qs_4 {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread thread1 = new Thread(() -> {
            String data1 = "Data from Thread1";
            try {
                String received = exchanger.exchange(data1);
                System.out.println("Thread1 received: " + received);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            String data2 = "Data from Thread2";
            try {
                String received = exchanger.exchange(data2);
                System.out.println("Thread2 received: " + received);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
