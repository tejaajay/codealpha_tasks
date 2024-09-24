package com.code_alpha.task1;

import java.util.Scanner;

public class SimpleBankingApp {
 
    private static double balance = 0.0; 
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;

        System.out.println("Welcome to the Simple Banking Application!");
        
        do {
            displayMenu(); 
            option = getOption();
            
            
            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    System.out.println("Exiting the application. Thank you for using our service!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        } while (option != 4); 
        
        scanner.close(); 
    }

    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Please choose an option (1-4): ");
    }

    private static int getOption() {
        int option = -1;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        return option;
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", balance);
    }

    private static void depositMoney() {
        System.out.print("Enter the amount to deposit: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0) {
                balance += amount;
                System.out.printf("Successfully deposited $%.2f. New balance: $%.2f\n", amount, balance);
            } else {
                System.out.println("Amount must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f\n", amount, balance);
            } else if (amount > balance) {
                System.out.println("Insufficient funds.");
            } else {
                System.out.println("Amount must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }
}
