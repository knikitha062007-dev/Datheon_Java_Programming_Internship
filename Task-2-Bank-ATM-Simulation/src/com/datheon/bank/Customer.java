
package com.datheon.bank;

public class Customer {

    private String customerName;
    private String pin;
    private Account account;

    public Customer(String customerName, String pin, Account account) {
        this.customerName = customerName;
        this.pin = pin;
        this.account = account;
    }

    public void verifyPIN(String enteredPIN) throws InvalidPINException {
        if (!pin.equals(enteredPIN)) {
            throw new InvalidPINException("Invalid PIN");
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public Account getAccount() {
        return account;
    }
}
