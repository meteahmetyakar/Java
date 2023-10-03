package com.exercises.bank;


import com.exercises.bank.dto.Transaction;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankAccount {

    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;
    private double balance;

    // used linkedhashmap because I want it to be sorted in order of insertion
    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();

    BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Long, String> getTransactions() { //deep copy

        Map<Long, String> txMap = new LinkedHashMap<>();
        for (var tx : transactions.entrySet()) {
            txMap.put(tx.getKey(), tx.getValue().toString());
        }
        return txMap;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }


     void commitTransaction(int routingNumber, long transactionId,
                           String customerId, double amount) {

        balance += amount;
        transactions.put(transactionId,
                new Transaction(routingNumber,
                        Integer.parseInt(customerId), transactionId, amount));
    }
}
