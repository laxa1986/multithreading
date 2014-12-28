package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.ThreadHelper;
import laxa.multithreading.framework.characteristics.*;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
@Throughput(Throughput.Value.MIDDLE)
@WriteOrder(Fairness.FAIR)
@Case_R$WR(fairness = Fairness.FAIR, value = "second reader will wait")
@Case_W$RW(fairness = Fairness.FAIR, value = "next will be reader")
public class T07_RWLockFair implements Strategy {
	@Override
	public String getName() {
		return "[RW Fair]";
	}

	private ReadWriteLock lock = new ReentrantReadWriteLock(true /* fair */);
	private Object o;

	public void write(Object o) {
		lock.writeLock().lock();
		try {
			ThreadHelper.doIt();
			this.o = o;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public Object read() {
		lock.readLock().lock();
		try {
			ThreadHelper.doIt();
			return this.o;
		} finally {
			lock.readLock().unlock();
		}
	}
}