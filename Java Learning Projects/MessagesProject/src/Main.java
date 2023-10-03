import java.security.PublicKey;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {
		Message message = new Message();

		//		Thread thread = new Thread(new Writer(message));
//		thread.start();

		//if thread will use one time this way may be efficient
		(new Thread(new Writer(message))).start();
		(new Thread(new Reader(message))).start();

	}
}