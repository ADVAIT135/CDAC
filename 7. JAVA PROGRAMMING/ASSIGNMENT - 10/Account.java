// Name : ADVAIT GURUNATH CHAVAN
// Email ID : advaitchavan135@gmail.com
// CDAC NOIDA JAVA PROGRAMMING Assignment 10 -  Question -  7 -- Account Class

import java.util.HashMap;
import java.util.Map;

//Class to maintain customer account details
@SuppressWarnings("unused")
class Account {
 private String accountNumber;
 private String customerName;
 private double balance;

 // Constructor initializes the account with a unique number, customer name, and an initial balance
 public Account(String accountNumber, String customerName, double initialBalance) {
     this.accountNumber = accountNumber;
     this.customerName = customerName;
     this.balance = initialBalance;
 }

 // Deposit money into the account
 public void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println("Deposited " + amount + " into account " + accountNumber + ". New balance: " + balance);
     } else {
         System.out.println("Invalid deposit amount.");
     }
 }

 // Withdraw money from the account if sufficient funds are available
 public void withdraw(double amount) {
     if (amount > 0 && amount <= balance) {
         balance -= amount;
         System.out.println("Withdrew " + amount + " from account " + accountNumber + ". New balance: " + balance);
     } else {
         System.out.println("Withdrawal failed for account " + accountNumber + ". Insufficient funds or invalid amount.");
     }
 }
 
  // Getters for account details
 public String getAccountNumber() {
     return accountNumber;
 }

 public double getBalance() {
     return balance;
 }

 // Overriding toString to provide a clean display of account details
 @Override
 public String toString() {
     return "Account Number: " + accountNumber +
            ", Customer: " + customerName +
            ", Balance: " + balance;
 }
}