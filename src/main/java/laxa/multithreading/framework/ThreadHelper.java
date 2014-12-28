package laxa.multithreading.framework;

/**
 * Author: Chekulaev Alexey
 * Date: 20.01.2012
 */
public final class ThreadHelper {
	private ThreadHelper(){}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void doIt() {
		Action action = StrategyExecutor.actionHolder.get();
		int duration = action.duration();
		if (duration > 0) {
			sleep(duration);
		}
	}

	// TODO: improve log: write logs in memory
	public static void log(String str) {
		System.out.println(Thread.currentThread().getId() + "\t: " + str);
	}
}