// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args)
	{

		System.out.println("From main thread");

		Thread anotherThread = new AnotherThread();
		anotherThread.setName("== Another Thread =="); // we can set name to threads
		anotherThread.start(); //this function calls run method after some operations

		new Thread() {
			public void run() {
				System.out.println("From anonymous class thread");
			}
		}.start();

		//or with lambdas

		new Thread(() -> System.out.println("From lambda thread")).start();

//		Thread myRunnableThread = new Thread(new MyRunnable());

		Thread myRunnableThread = new Thread(new MyRunnable() {
			@Override
			public void run() {
				System.out.println("From the anonymous class implementation");
				try {
					anotherThread.join();
					System.out.println("Another thread terminated in myRunnable");
					//this exception comes from myRunnableThread, not anotherThread.
					//myRunnableThread.interrupt()
				} catch (InterruptedException e)
				{
					System.out.println("anotherThread couldn't wait after all. it was interrupted");
				}
			}
		});

		myRunnableThread.start();

		System.out.println("Again from the main thread");

		//we cannot call second time to start method it throws exception, so we can call start method of each instance once such as streams
//		anotherThread.start();



	}
}