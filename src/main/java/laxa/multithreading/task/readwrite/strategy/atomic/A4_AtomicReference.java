package laxa.multithreading.task.readwrite.strategy.atomic;

import laxa.multithreading.task.readwrite.strategy.Strategy;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Author: Chekulaev Alexey
 * Date: 19.02.12
 */
public class A4_AtomicReference implements Strategy {
	@Override
	public String getName() {
		return "";
	}

	private AtomicReference<Object> r;

	public void write(Object o) {
		r.set(o);
	}


	public Object read() {
		return r.get();
	}
}