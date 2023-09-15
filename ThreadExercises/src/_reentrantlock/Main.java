package _reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {

		ReentrantLock lock = new ReentrantLock();
		BankAccountReentrant account = new BankAccountReentrant(1000,"12345-678", lock);

		new Thread(new Runnable() {
			@Override
			public void run() {
				account.deposit(300, "Thread 1");
				account.withdraw(50, "Thread 1");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {

				account.deposit(203.75, "Thread 2");
				account.withdraw(100,"Thread 2");
			}
		}).start();

		lock.lock();
		System.out.println(account.getBalance());
		lock.unlock();

	}

}
