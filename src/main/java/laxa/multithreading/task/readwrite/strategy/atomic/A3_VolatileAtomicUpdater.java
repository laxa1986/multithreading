package laxa.multithreading.task.readwrite.strategy.atomic;

import laxa.multithreading.task.readwrite.strategy.Strategy;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class A3_VolatileAtomicUpdater implements Strategy {
	@Override
	public String getName() {
		return "";
	}

	public static class MyClass {
	}

	private volatile MyClass field;

	private static final AtomicReferenceFieldUpdater<A3_VolatileAtomicUpdater, MyClass> updater =
			AtomicReferenceFieldUpdater.newUpdater(A3_VolatileAtomicUpdater.class, MyClass.class, "field");

	public MyClass read() {
		return field;
	}

	public void write(Object value) {
		updater.compareAndSet(this, this.field, (MyClass)value);
	}
}