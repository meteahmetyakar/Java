package issues.thread_starvation;

import color.ThreadColor;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ThreadStarvation {

	private static Object lock = new Object();

	public static void main(String[] args)
	{
		Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED.getColor()), "Priority 10");
		Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE.getColor()), "Priority 8");
		Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN.getColor()), "Priority 6");
		Thread t4 = new Thread(new Worker(ThreadColor.ANSI_CYAN.getColor()), "Priority 4");
		Thread t5 = new Thread(new Worker(ThreadColor.ANSI_PURPLE.getColor()), "Priority 2");

		// priority is only advice for OS and JVM. These assignments do not contain certainly
		// so most of the scenarios this will not be called sequentially
		// also, we have a problem which name Thread Starvation. Thread's take lock and it doesn't release it long time
		// if we check Worker class, run method is synchronized, and it prints a thing then release lock but in the run it doesn't seem like that		t1.setPriority(10);
		// we'll solve this problem with Fair Lock

		t1.setPriority(10);
		t2.setPriority(8);
		t3.setPriority(6);
		t4.setPriority(4);
		t5.setPriority(2);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

	}

	private static class Worker implements Runnable
	{
		private int runCount = 1;
		private String threadColor;

		public Worker(String threadColor) {
			this.threadColor = threadColor;
		}

		@Override
		public void run() {
			for(int i = 0; i<100; i++)
			{
				synchronized (lock) {
					System.out.format(threadColor + "%s: runCount = %d\n",
							Thread.currentThread().getName(), runCount++);
					//execute critical section of code
				}
			}
		}
	}
}