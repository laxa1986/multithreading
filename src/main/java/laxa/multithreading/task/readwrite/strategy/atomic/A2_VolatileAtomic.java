package laxa.multithreading.task.readwrite.strategy.atomic;

import laxa.multithreading.task.readwrite.strategy.RwStrategy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class A2_VolatileAtomic implements RwStrategy {
	private volatile AtomicLong field;

	@Override
	public void write(Object o) {
//		this.field = o;
		field.set((Long) o);
	}

	@Override
	public Object read() {
		return field.get();
	}
}