package com.exercises.bank.dto;

public record Transaction(int routingNumber, int customerId, long transactionId, double transactionAmount) {


    @Override
    public String toString() {
        return "%015d:%020d:%015d:%012.2f".formatted(routingNumber, customerId,
                transactionId, transactionAmount);
    }
}
