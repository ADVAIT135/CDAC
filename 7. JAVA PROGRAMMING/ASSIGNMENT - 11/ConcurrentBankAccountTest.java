// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  1 -- ConcurrentBankAccountTest

public class ConcurrentBankAccountTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        
        Runnable depositTask = () -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(100);
                try { Thread.sleep(50); } catch (InterruptedException e) { }
            }
        };
        
        Runnable withdrawTask = () -> {
            for (int i = 0; i < 5; i++) {
                account.withdraw(50);
                try { Thread.sleep(50); } catch (InterruptedException e) { }
            }
        };
        
        Thread t1 = new Thread(depositTask, "Depositor");
        Thread t2 = new Thread(withdrawTask, "Withdrawer");
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) { e.printStackTrace(); }
        
        System.out.println("Final balance: " + account.getBalance());
    }
}