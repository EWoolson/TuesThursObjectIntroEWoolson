package com.company;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Bank {
    private ArrayList<BankAccount> allAccounts;
    private ArrayList<Customer> allCustomers;

    public Bank(){
        allAccounts = new ArrayList<BankAccount>();
        allCustomers = new ArrayList<Customer>();
    }

    public void doBanking(){
        var menuReader = new Scanner(System.in);
        while(true){
            printMenu();
            var userChoice = menuReader.nextInt();
            switch (userChoice){
                case 1:
                    System.exit(0);
                case 2:
                    addCustomer(menuReader);
                    break;
                case 3:
                    Optional<Customer> current = selectCustomer(menuReader);
                    if(current.isPresent())
                        doCustomerMenu(menuReader, current.get());
                    else
                        System.out.println("No customer with that ID found");
                    break;
                case 4:
                    doYearlyMaintenance();
                default:
                    System.out.println("Please choose one of the menu options");
            }
        }
    }

    private void doYearlyMaintenance() {
        int i;
        System.out.println("Here is a list of all the accounts with interest:");
        for (var currentAccount: allAccounts){
            currentAccount.addInterest();
            System.out.println("Account ID: "+ currentAccount.getAccountID()+ " has a balance of " + currentAccount.checkBalance());
        }
    }

    private void doCustomerMenu(Scanner menuReader, Customer currentCustomer) {
        while (true){
            printCustomerMenu();
            var customerChoice = menuReader.nextInt();
            switch(customerChoice){
                case 1:
                    openCustomerAccount(menuReader, currentCustomer);
                    break;
                case 2:
                    closeCustomerAccount(menuReader, currentCustomer);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Input, please choose one of the options listed");
            }
        }
    }

    private void closeCustomerAccount(Scanner menuReader, Customer currentCustomer) {
        System.out.println("Closing Account.....");
        System.out.println("What is the account number that you would like to close?");
        var accountNum = menuReader.nextInt();
        Optional<BankAccount> accountToClose = currentCustomer.closeAccount(accountNum);
        if(accountToClose.isPresent()){
            allAccounts.remove(accountToClose.get());
        }
    }

    private void openCustomerAccount(Scanner menuReader, Customer currentCustomer) {
        System.out.println("Creating new account......");
        System.out.print("How much money would you like the starting deposit to be: ");
        var initialDeposit = menuReader.nextDouble();
        var newAccount = currentCustomer.openAccount(initialDeposit);
        allAccounts.add(newAccount);
    }

    private void printCustomerMenu() {
        System.out.println("*****************************************");
        System.out.println("What do you want to do with this customer");
        System.out.println("   [1] Open an account");
        System.out.println("   [2] Close an account");
        System.out.println("   [3] Return to main menu");
        System.out.println("*****************************************");
        System.out.print("Enter Choice: ");
    }

    private Optional<Customer> selectCustomer(Scanner reader) {
        System.out.print("Customer ID of customer to select: ");
        var idToFind = reader.nextInt();
        for(var currentCustomer: allCustomers){
            if(currentCustomer.getID() == idToFind)
                return Optional.of(currentCustomer);
        }
        return Optional.empty();
    }

    private void addCustomer(Scanner inputReader) {
        inputReader.nextLine(); //eat the orphan newline from previous nextInt call
        System.out.print("What is the new customer's name: ");
        var customerName = inputReader.nextLine();
        System.out.print("What is the new Customer's Tax ID (SSN): ");
        var taxID = inputReader.nextInt();
        var newCustomer = new Customer(customerName, taxID);
        allCustomers.add(newCustomer);
    }

    private void printMenu() {
        System.out.println("======================================================");
        System.out.println("What would you like to do next (Select the number): ");
        System.out.println("   [1] Close Program");
        System.out.println("   [2] Add Customer");
        System.out.println("   [3] Select Customer by ID");
        System.out.println("   [4] Do the yearly maintenance and show all accounts");
        System.out.println("======================================================");
        System.out.print("Enter choice: ");
    }
}
