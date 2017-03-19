package laxa.multithreading.task.increment.strategy;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
@ThreadSafe
public class T1_Synchronized implements IncrementStrategy {
	@GuardedBy("this")
	private int i = 0;
	
	@Override
	public synchronized int next() {
		return ++i;
	}
}