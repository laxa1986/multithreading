package laxa.multithreading.framework;

/**
 * Author: Chekulaev Alexey
 * Date: 20.01.2012
 */
public final class ThreadHelper {
	public static final ThreadLocal<Integer> durationHolder = new ThreadLocal<>();

	private ThreadHelper(){}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void setDuration(int duration) {
		durationHolder.set(duration);
	}

	public static void doIt() {
		Integer duration = durationHolder.get();
		if (duration != null && duration > 0) {
			sleep(duration);
		}
	}

	// TODO: improve log: write logs in memory
	public static void log(String str) {
		System.out.println(Thread.currentThread().getId() + "\t: " + str);
	}
}