package dev.client;

import com.exercises.bank.Bank;
import com.exercises.bank.BankAccount;
import com.exercises.bank.BankCustomer;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank(1);
        bank.addCustomer(new StringBuilder("mete"), 500,
        1000);

        BankCustomer cust = bank.getCustomer("1");
        System.out.println(cust);

        if (bank.doTransaction(cust.getCustomerId(), BankAccount.AccountType.CHECKING,
                5)) {
            System.out.println(cust);
        }

        if (bank.doTransaction(cust.getCustomerId(), BankAccount.AccountType.CHECKING,
                -535)) {
            System.out.println(cust);
        }

        // cust.getAccounts().clear(); <- this throw an error because getAccounts() returns a unmodifiable list

        BankAccount checking = cust.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));


        cust.getAccount(BankAccount.AccountType.CHECKING).getTransactions().clear(); // transactions cleared, but customer not affected to that because getAccount returns deep copy of map

        System.out.println("------------------");
        cust.getAccount(BankAccount.AccountType.CHECKING).getTransactions()
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }
}