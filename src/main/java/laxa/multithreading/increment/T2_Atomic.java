package laxa.multithreading.increment;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class T2_Atomic implements IncTask {
	private AtomicInteger i = new AtomicInteger(0);

	public int next() {
		return i.incrementAndGet();
	}
}