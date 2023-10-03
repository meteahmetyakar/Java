import java.util.Random;

public class Message {
	private String message;
	private boolean empty = true;

	public synchronized String read()
	{
		//using loop is important because thread can wake up by another way. We want to continue when it wakes up and when empty is true
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		empty = true;
		notifyAll();
		return message;
	}

	public synchronized void write(String message)
	{
		//using loop is important because thread can wake up by another way. We want to continue when it wakes up and when empty is false
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		empty = false;
		this.message = message;
		notifyAll();
	}
}
