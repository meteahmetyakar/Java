package _synchronized;

import java.sql.SQLOutput;

public class BankAccount {

	private double balance;
	private String accountNumber;

	public BankAccount(double balance, String accountNumber) {
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	public synchronized double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public String toString() {
		return "_synchronized.BankAccount{" +
				"balance=" + balance +
				", accountNumber='" + accountNumber + '\'' +
				'}';
	}

	public synchronized void deposit(double amount, String name)
	{
		System.out.println("Deposit process starting in " + name);
		balance += amount;
		System.out.println("Deposit process end in " + name + " and balance is " + balance);

	}

	public synchronized void withdraw(double amount, String name)
	{
		System.out.println("Withdraw process starting in " + name);
		balance -= amount;
		System.out.println("Withdraw process end in " + name + " and balance is " + balance);

	}

}
