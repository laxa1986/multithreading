package laxa.multithreading.readwrite.strategy.atomic;

import laxa.multithreading.readwrite.strategy.Strategy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class A2_VolatileAtomic implements Strategy {
	@Override
	public String getName() {
		return "";
	}

	private volatile AtomicLong field;

	public void write(Object o) {
//		this.field = o;
		field.set((Long) o);
	}

	public Object read() {
		return field.get();
	}
}