// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  7 -- Bank Class

import java.util.HashMap;
import java.util.Map;


//Class representing a bank that holds multiple accounts
class Bank {
 // Using a HashMap to store accounts with the account number as the key for quick lookup
 private Map<String, Account> accounts;

 public Bank() {
     accounts = new HashMap<>();
 }

 // Add an account to the bank
 public void addAccount(Account account) {
     if (accounts.containsKey(account.getAccountNumber())) {
         System.out.println("Account with number " + account.getAccountNumber() + " already exists.");
     } else {
         accounts.put(account.getAccountNumber(), account);
         System.out.println("Account added: " + account);
     }
 }

 // Remove an account from the bank using the account number
 public void removeAccount(String accountNumber) {
     if (accounts.containsKey(accountNumber)) {
         Account removed = accounts.remove(accountNumber);
         System.out.println("Account removed: " + removed);
     } else {
         System.out.println("Account " + accountNumber + " not found.");
     }
 }

 // Deposit money into a specific account
 
 public void deposit(String accountNumber, double amount) {
     Account account = accounts.get(accountNumber);
     if (account != null) {
         account.deposit(amount);
     } else {
         System.out.println("Account " + accountNumber + " not found for deposit.");
     }
 }

 // Withdraw money from a specific account
 public void withdraw(String accountNumber, double amount) {
     Account account = accounts.get(accountNumber);
     if (account != null) {
         account.withdraw(amount);
     } else {
         System.out.println("Account " + accountNumber + " not found for withdrawal.");
     }
 }
 
 // Display details of all accounts held by the bank
 public void displayAccounts() {
     System.out.println("----- Bank Accounts -----");
     for (Account account : accounts.values()) {
         System.out.println(account);
     }
 }
}