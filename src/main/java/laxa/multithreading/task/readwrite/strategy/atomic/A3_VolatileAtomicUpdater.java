package laxa.multithreading.task.readwrite.strategy.atomic;

import laxa.multithreading.task.readwrite.strategy.RwStrategy;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class A3_VolatileAtomicUpdater implements RwStrategy {
	public static class MyClass {
	}

	private volatile MyClass field;

	private static final AtomicReferenceFieldUpdater<A3_VolatileAtomicUpdater, MyClass> updater =
			AtomicReferenceFieldUpdater.newUpdater(A3_VolatileAtomicUpdater.class, MyClass.class, "field");

	@Override
	public MyClass read() {
		return field;
	}

	@Override
	public void write(Object value) {
		updater.compareAndSet(this, this.field, (MyClass)value);
	}
}