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
                        doCustomerMenu(menuReader);
                    else
                        System.out.println("No customer with that ID found");
                    break;
                default:
                    System.out.println("Please choose one of the menu options");
            }
        }
    }

    private void doCustomerMenu(Scanner menuReader) {
        System.out.println("We will finish this next week");
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
        System.out.println("What would you like to do next (Select the number): ");
        System.out.println("   [1] Close Program");
        System.out.println("   [2] Add Customer");
        System.out.println("   [3] Select Customer by ID");
        System.out.print("Enter choice: ");
    }
}
