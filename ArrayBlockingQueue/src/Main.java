import java.util.concurrent.*;

// Here, I implemented ArrayBlockingQueue instead of ArrayList to the ProducerConsumer

public class Main {
	public static void main(String[] args) {

		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);

		ExecutorService executorService = Executors.newFixedThreadPool(3);

		Producer producer = new Producer(buffer, ThreadColor.ANSI_YELLOW.getColor());
		Consumer consumer1 = new Consumer(buffer, ThreadColor.ANSI_PURPLE.getColor());
		Consumer consumer2 = new Consumer(buffer, ThreadColor.ANSI_BLACK.getColor());

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