public class Main {
	public static void main(String[] args)
	{

		Countdown countdown = new Countdown();

		CountdownThread t1 = new CountdownThread(countdown);
		t1.setName("Thread 1");

		CountdownThread t2 = new CountdownThread(countdown);
		t2.setName("Thread 2");

		t1.start();
		t2.start();
	}
}




class Countdown {

	private int counter;
	public synchronized void doCountdown() {
		String color = switch (Thread.currentThread().getName()) {
			case "Thread 1" -> ThreadColor.ANSI_RED.getColor();
			case "Thread 2" -> ThreadColor.ANSI_PURPLE.getColor();
			default -> ThreadColor.ANSI_GREEN.getColor();
		};

		for(counter = 10; counter > 0; counter--)
			System.out.println(color + Thread.currentThread().getName() + ": i = " + counter);

	}
}

class CountdownThread extends Thread {

	private Countdown threadCountdown;

	public CountdownThread(Countdown countdown) {
		threadCountdown = countdown;
	}


	@Override
	public void run() {
		threadCountdown.doCountdown();
	}
}
