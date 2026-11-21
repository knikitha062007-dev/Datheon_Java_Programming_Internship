
package com.datheon.bank;

public abstract class Account {

    private String accountNumber;
    private double balance;

    protected Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void deductBalance(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance");
        }

        deductBalance(amount);
    }

    public abstract double calculateInterest();
}
