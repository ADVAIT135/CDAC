// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  7 -- Test Bank Class

public class TestBank {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create some accounts
        Account acc1 = new Account("AC001", "Advait", 1000.0);
        Account acc2 = new Account("AC002", "Chavan", 2000.0);
        Account acc3 = new Account("AC003", "DeltaCorp", 1500.0);

        // Add the accounts to the bank
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);
        System.out.println();

        // Display all accounts
        bank.displayAccounts();
        System.out.println();

        // Demonstrate deposit and withdrawal functions
        bank.deposit("AC001", 500.0);   // Deposit into Advait's account
        bank.withdraw("AC002", 300.0);  // Withdraw from Chavan's account
        bank.withdraw("AC003", 2000.0); // Attempt a withdrawal with insufficient funds
        System.out.println();

        // Display updated account details
        bank.displayAccounts();
        System.out.println();

        // Remove an account and display accounts again
        bank.removeAccount("AC002");
        System.out.println();
        bank.displayAccounts();
    }
}