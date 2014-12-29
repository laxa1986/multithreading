package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.Strategy;

public interface RwStrategy extends Strategy {
	void write(Object o);

	Object read();
}