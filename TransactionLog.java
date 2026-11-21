
package com.datheon.bank;

import java.time.LocalDateTime;

public class TransactionLog {

    private String timestamp;
    private String transactionType;
    private double resultingBalance;

    public TransactionLog(String transactionType, double resultingBalance) {
        this.timestamp = LocalDateTime.now().toString();
        this.transactionType = transactionType;
        this.resultingBalance = resultingBalance;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getResultingBalance() {
        return resultingBalance;
    }
}
