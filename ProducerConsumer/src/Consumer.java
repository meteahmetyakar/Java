import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {
	private List<String> buffer;
	private String color;
	private ReentrantLock bufferLock;
	private int counter = 0;

	public Consumer(List<String> buffer, String color, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.color = color;
		this.bufferLock = bufferLock;
	}

	@Override
	public void run() {
		while (true) {
			/*

			synchronized (buffer)
			{
				if(buffer.isEmpty()) {
					continue;
				}
				if(buffer.get(0).equals("EOF")) {
					System.out.println(color + "Exiting");
					break;
				} else {
					System.out.println(color + "Removed " + buffer.remove(0));
				}
			}

			*/


			/*

			bufferLock.lock();
			try {
				if(buffer.isEmpty()) {
					continue;
				}
				if(buffer.get(0).equals("EOF")) {
					System.out.println(color + "Exiting");
					break;
				} else {
					System.out.println(color + "Removed " + buffer.remove(0));
				}

			} finally {
				bufferLock.unlock();
			}

			 */

			//trying to take the object lock, if it true it can enter the if block, otherwise it doesn't

			if (bufferLock.tryLock()) {
				try {
					if (buffer.isEmpty()) {
						continue;
					}
					System.out.println(color + "The counter = " + counter);
					counter = 0;
					if (buffer.get(0).equals("EOF")) {
						System.out.println(color + "Exiting");
						break;
					} else {
						System.out.println(color + "Removed " + buffer.remove(0));
					}

				} finally {
					bufferLock.unlock();
				}
			} else {
				counter++;
			}


		}
	}
}
