package _synchronized;

public class Main {

	public static void main(String[] args) {
		BankAccount account = new BankAccount(1000,"12345-678");

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

		synchronized (account)
		{
			System.out.println(account.getBalance());
		}

	}




}
