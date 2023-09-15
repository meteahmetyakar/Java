import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {
	private ArrayBlockingQueue<String> buffer;
	private String color;

	public Consumer(ArrayBlockingQueue<String> buffer, String color) {
		this.buffer = buffer;
		this.color = color;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (buffer) // private ArrayBlockingQueue<String> buffer;
			{
				try {
					if (buffer.isEmpty()) {
						continue;
					}

					// -->	 In the non-synchronized scenario	<--
					//At first glance everything seems fine, we check buffer is Empty if it isn't we peek it
					//So problem can be where place?
					//Problem exactly here, on this line.
					//buffer cannot be empty until this line, but here thread can be suspended then remove element from it any reason
					//after all of that when we call peek() method, boom!
					//because thread was suspended and now buffer is empty, but we called peek method.
					//peek method returns null because of buffer is empty then we call equal method with null
					//this will throw an exception

					//So what that all means is that we may still have to add synchronization code
					//when using thread safe classes like ArrayBlockingQueue.

					if (buffer.peek().equals("EOF")) {
						System.out.println(color + "Exiting");
						break;
					} else {
						System.out.println(color + "Removed " + buffer.take());
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
