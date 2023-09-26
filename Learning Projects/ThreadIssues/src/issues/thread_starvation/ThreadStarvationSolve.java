package issues.thread_starvation;

import color.ThreadColor;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadStarvationSolve {

	//our lock is fair so this will be called sequentially
	private static ReentrantLock fairLock = new ReentrantLock(true);

	public static void main(String[] args)
	{
		Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED.getColor()), "Priority 10");
		Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE.getColor()), "Priority 8");
		Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN.getColor()), "Priority 6");
		Thread t4 = new Thread(new Worker(ThreadColor.ANSI_CYAN.getColor()), "Priority 4");
		Thread t5 = new Thread(new Worker(ThreadColor.ANSI_PURPLE.getColor()), "Priority 2");

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
				fairLock.lock();
				try {
					System.out.format(threadColor + "%s: runCount = %d\n",
							Thread.currentThread().getName(), runCount++);
					//execute critical section of code
				} finally {
					fairLock.unlock();
				}

			}

		}
	}
}