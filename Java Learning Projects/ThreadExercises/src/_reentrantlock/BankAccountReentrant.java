package _reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccountReentrant {
	ReentrantLock lock;
	private double balance;
	private String accountNumber;

	public BankAccountReentrant(double balance, String accountNumber, ReentrantLock lock) {
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.lock = lock;
	}

	public double getBalance() {

		try {
			lock.lock();
			return balance;
		} finally {
			lock.unlock();
		}
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

	public void deposit(double amount, String name)
	{
		try {
			lock.lock();
			System.out.println("Deposit process starting in " + name);
			balance += amount;
			System.out.println("Deposit process end in " + name + " and balance is " + balance);
		} finally {
			lock.unlock();
		}

	}

	public void withdraw(double amount, String name)
	{
		try
		{
			lock.lock();
			System.out.println("Withdraw process starting in " + name);
			balance -= amount;
			System.out.println("Withdraw process end in " + name + " and balance is " + balance);
		} finally {
			lock.unlock();
		}

	}

}
