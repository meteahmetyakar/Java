package Singleton;

/*
	The preceding implementation works fine in the case of the single-threaded environment,
	but when it comes to multi-threaded systems, it can cause issues if multiple threads are inside the condition at the same time.
 */
public class LazyInitializedSingleton {

	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton(){}

	public static LazyInitializedSingleton getInstance() {
		if (instance == null) {
			instance = new LazyInitializedSingleton();
		}
		return instance;
	}

}
