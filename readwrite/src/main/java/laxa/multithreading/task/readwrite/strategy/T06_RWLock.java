package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.ThreadHelper;
import laxa.multithreading.framework.characteristics.*;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: Chekulaev Alexey
 * Date: 26.02.12
 */
@ThreadSafe
@Throughput(Throughput.Value.MIDDLE)
@WriteOrder(Fairness.UNFAIR_READ_OPTIMIZATION)
@Case_R$WR(fairness = Fairness.FAIR, value = "second reader will wait")
@Case_W$RW(fairness = Fairness.UNDEFINED, value = "")
public class T06_RWLock implements RwStrategy {

	private final ReadWriteLock lock;

	@GuardedBy("lock")
	private Object o;

	public T06_RWLock(boolean fair) {
		this.lock = new ReentrantReadWriteLock(fair);
	}

	@Override
	public void write(Object o) {
		Lock wLock = lock.writeLock();
		wLock.lock();
		try {
			ThreadHelper.doIt();
			this.o = o;
		} finally {
			wLock.unlock();
		}
	}

	@Override
	public Object read() {
		Lock rLock = lock.readLock();
		rLock.lock();
		try {
			ThreadHelper.doIt();
			return this.o;
		} finally {
			rLock.unlock();
		}
	}
}