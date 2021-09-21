package com.company;

public class BankAccount {
    private double balance;
    private float interestRate;
    private int accountID;
    private static int nextID = 100 ;

    public BankAccount(){
        interestRate = .02f;
        accountID = nextID;
        nextID++;
    }
    public BankAccount(double initialBalance, float initialRate){
        balance = initialBalance;
        interestRate = initialRate;
        accountID = nextID;
        nextID++;
    }

    public int getAccountID(){
        return accountID;
    }
    public void deposit(double amount){
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
    public double addInterest(){
        balance = balance * interestRate + balance;
        return balance;
    }
    public double checkBalance(){
        return balance;
    }
}
