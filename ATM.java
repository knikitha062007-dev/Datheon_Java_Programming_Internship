
package com.datheon.bank;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Collect customer details
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Set ATM PIN: ");
        String pin = scanner.nextLine();

        // Select account type
        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.print("Enter choice: ");

        int accountType = scanner.nextInt();

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();

        Account account;

        if (accountType == 1) {

            System.out.print("Enter interest rate: ");
            double interestRate = scanner.nextDouble();

            account = new SavingsAccount(
                    accountNumber, balance, interestRate
            );

        } else if (accountType == 2) {

            System.out.print("Enter interest rate: ");
            double interestRate = scanner.nextDouble();

            System.out.print("Enter overdraft limit: ");
            double overdraftLimit = scanner.nextDouble();

            account = new CheckingAccount(
                    accountNumber, balance,
                    interestRate, overdraftLimit
            );

        } else {
            System.out.println("Invalid account type.");
            scanner.close();
            return;
        }

        Customer customer = new Customer(
                customerName, pin, account
        );

        // Store transaction history
        ArrayList<TransactionLog> transactions = new ArrayList<>();

        // PIN verification
        try {
            System.out.print("\nEnter PIN to access ATM: ");
            String enteredPIN = scanner.next();

            customer.verifyPIN(enteredPIN);

            System.out.println("\nPIN verified successfully.");
            System.out.println("Welcome, " + customer.getCustomerName());

        } catch (InvalidPINException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        // Prevent repeated interest crediting in this session
        boolean interestApplied = false;

        // ATM menu
        while (true) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Apply Interest");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.printf(
                            "Current Balance: %.2f%n",
                            account.getBalance()
                    );
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();

                    try {
                        account.deposit(depositAmount);

                        transactions.add(
                                new TransactionLog(
                                        "DEPOSIT",
                                        account.getBalance()
                                )
                        );

                        System.out.println("Deposit successful.");

                        System.out.printf(
                                "New Balance: %.2f%n",
                                account.getBalance()
                        );

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    try {
                        account.withdraw(withdrawAmount);

                        transactions.add(
                                new TransactionLog(
                                        "WITHDRAWAL",
                                        account.getBalance()
                                )
                        );

                        System.out.println("Withdrawal successful.");

                        System.out.printf(
                                "New Balance: %.2f%n",
                                account.getBalance()
                        );

                    } catch (InsufficientFundsException |
                             IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    if (interestApplied) {
                        System.out.println(
                                "Interest has already been applied in this session."
                        );
                        break;
                    }

                    double interest = account.calculateInterest();

                    System.out.printf(
                            "Calculated Interest: %.2f%n",
                            interest
                    );

                    if (interest > 0) {
                        account.deposit(interest);

                        transactions.add(
                                new TransactionLog(
                                        "INTEREST CREDITED",
                                        account.getBalance()
                                )
                        );

                        System.out.println("Interest credited successfully.");

                        System.out.printf(
                                "New Balance: %.2f%n",
                                account.getBalance()
                        );
                    } else {
                        System.out.println("No interest to credit.");
                    }

                    interestApplied = true;
                    break;

                case 5:
                    System.out.println("\n======= TRANSACTION HISTORY =======");

                    if (transactions.isEmpty()) {
                        System.out.println("No transactions available.");
                    } else {

                        for (TransactionLog log : transactions) {

                            System.out.println(
                                    "Timestamp: " + log.getTimestamp()
                            );

                            System.out.println(
                                    "Transaction: " + log.getTransactionType()
                            );

                            System.out.printf(
                                    "Resulting Balance: %.2f%n",
                                    log.getResultingBalance()
                            );

                            System.out.println("--------------------------------");
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using our ATM.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid menu choice.");
            }
        }
    }
}
