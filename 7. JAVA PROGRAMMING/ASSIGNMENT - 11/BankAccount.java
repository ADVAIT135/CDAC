// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 11 -  Question -  1 -- BankAccount

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() +
                           " deposited: " + amount + " | New balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() +
                               " withdrew: " + amount + " | New balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() +
                               " attempted to withdraw: " + amount + " | Insufficient funds");
        }
    }

    public double getBalance() {
        return balance;
    }
}
