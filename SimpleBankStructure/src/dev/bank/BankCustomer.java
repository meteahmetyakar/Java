package dev.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {

    private static int lastCustomerId = 1;

    private final StringBuilder name;
    private final int customerId;
    private final List<BankAccount> accounts = new ArrayList<>();

    BankCustomer(StringBuilder name, double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerId = lastCustomerId++;
        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(BankAccount.AccountType.SAVINGS, savingsAmount));
    }

    public StringBuilder getName() {
        return new StringBuilder(name);
    } //defensive copy

    public String getCustomerId() {
        return Integer.toString(customerId);
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    } //it returns unmodifiable list

    public BankAccount getAccount(BankAccount.AccountType type) {

        for (var account : accounts) {
            if (account.getAccountType() == type) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String toString() {

        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n".formatted(name, customerId,
                String.join("\n\t", accountStrings));
    }
}
