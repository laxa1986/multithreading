package laxa.multithreading.task.readwrite.strategy.atomic;

import laxa.multithreading.task.readwrite.strategy.RwStrategy;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Author: Chekulaev Alexey
 * Date: 19.02.12
 */
public class A4_AtomicReference implements RwStrategy {
	private AtomicReference<Object> r;

	@Override
	public void write(Object o) {
		r.set(o);
	}

	@Override
	public Object read() {
		return r.get();
	}
}