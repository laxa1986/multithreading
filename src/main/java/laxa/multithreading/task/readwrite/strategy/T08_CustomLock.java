package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.ThreadHelper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Chekulaev Alexey
 * Date: 18.03.12
 */
public class T08_CustomLock implements RwStrategy {
	private Object o;

	private Lock rLock = new ReentrantLock(false /* unfair */);
	private Condition condition = rLock.newCondition();
	private volatile int rCnt = 0; // this variable not in synchronized section

	private void wait(Condition condition) {
		try {
			condition.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void write(Object o) {
		rLock.lock();
		try {
//			ThreadHelper.log("try to write. rCnt="+rCnt);
			while (rCnt > 0) {
				wait(condition);
			}
//			ThreadHelper.log("write");
			ThreadHelper.doIt();
			this.o = o;
//			ThreadHelper.log("write finish");
			condition.signal(); // notify next writer
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public Object read() {
		rLock.lock();
		try {
			rCnt++;
//			ThreadHelper.log("read. rCnt="+rCnt);
		} finally {
			rLock.unlock();
		}

		ThreadHelper.doIt();
		Object result = this.o;

		rLock.lock();
		try {
			rCnt--;
//			ThreadHelper.log("read finished. rCnt="+rCnt);
			if (rCnt == 0) {
				condition.signal();  // notify next writer
			}
		} finally {
			rLock.unlock();
		}

		return result;
	}
}