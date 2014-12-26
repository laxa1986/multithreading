package laxa.multithreading.readwrite.framework;

import laxa.multithreading.readwrite.action.Action;

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
	
	public static void log(String str) {
		System.out.println(Thread.currentThread().getId() + "\t: " + str);
	}
}