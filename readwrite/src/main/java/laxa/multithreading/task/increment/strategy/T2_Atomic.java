package laxa.multithreading.task.increment.strategy;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
@ThreadSafe
public class T2_Atomic implements IncrementStrategy {
	private final AtomicInteger i = new AtomicInteger(0);

	@Override
	public int next() {
		return i.incrementAndGet();
	}
}