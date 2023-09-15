import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		List<String> buffer = new ArrayList<>();
		ReentrantLock bufferLock = new ReentrantLock();

		ExecutorService executorService = Executors.newFixedThreadPool(3);

		Producer producer = new Producer(buffer, ThreadColor.ANSI_YELLOW.getColor(), bufferLock);
		Consumer consumer1 = new Consumer(buffer, ThreadColor.ANSI_PURPLE.getColor(), bufferLock);
		Consumer consumer2 = new Consumer(buffer, ThreadColor.ANSI_BLACK.getColor(), bufferLock);

		executorService.execute(producer);
		executorService.execute(consumer1);
		executorService.execute(consumer2);

		//There are 3 threads (3 is the limit of pool - line 14) in pool that's why this submit() method added to queue, and it waits for any thread finish
		Future<String> future = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println(ThreadColor.ANSI_WHITE.getColor() + "I'm being printed for the Callable class");
				return "This is the callable result";
			}
		});

		try {
			System.out.println(future.get());
		} catch (ExecutionException e)
		{
			System.out.println("Something went wrong");
		} catch (InterruptedException e)
		{
			System.out.println("Thread running the task was interrupted");
		}

		executorService.shutdown();

	}
}