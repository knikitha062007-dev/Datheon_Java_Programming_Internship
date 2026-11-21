
package com.datheon.bank;

public class CheckingAccount extends Account {

    private double interestRate;
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance,
                           double interestRate, double overdraftLimit) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double calculateInterest() {
        return (getBalance() * interestRate) / 100;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (amount > getBalance() + overdraftLimit) {
            throw new InsufficientFundsException("Overdraft limit exceeded");
        }

        deductBalance(amount);
    }
}
