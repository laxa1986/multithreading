package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.ThreadHelper;
import laxa.multithreading.framework.characteristics.*;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Chekulaev Alexey
 * Date: 19.02.12
 */
@ThreadSafe
@Throughput(Throughput.Value.MIN)
@WriteOrder(Fairness.UNDEFINED)
@Case_R$WR(fairness = Fairness.UNDEFINED, value = "next will be ?")
@Case_W$RW(fairness = Fairness.UNDEFINED, value = "next will be ?")
public class T05_ReentrantLock implements RwStrategy {

	private final Lock lock = new ReentrantLock(false /* not fair */);
	@GuardedBy("lock")
	private Object o;

	@Override
	public void write(Object o) {
		lock.lock();
		try {
			ThreadHelper.doIt();
			this.o = o;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Object read() {
		lock.lock();
		try {
			ThreadHelper.doIt();
			return this.o;
		} finally {
			lock.unlock();
		}
	}
}