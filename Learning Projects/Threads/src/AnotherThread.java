public class AnotherThread extends Thread{

	@Override
	public void run() {
		System.out.println("From " + currentThread().getName() + " thread");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("AnotherThread woke me up");
			return;
		}

		System.out.println("Three seconds have passed and AnotherThread awake");
	}
}
