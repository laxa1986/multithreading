package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.ThreadHelper;
import laxa.multithreading.framework.characteristics.*;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Author: Chekulaev Alexey
 * Date: 18.01.2012
 */
@ThreadSafe
@Throughput(Throughput.Value.MIDDLE)
@WriteOrder(Fairness.UNFAIR_READ_OPTIMIZATION)
@Case_R$WR(fairness = Fairness.UNFAIR_READ_OPTIMIZATION, value = "reader start read")
@Case_W$RW(fairness = Fairness.UNFAIR_THREAD_PRIORITIES, value = "next will be ? (both R & W wait on synchronized(rLock))")
public class T02_StrategyRead implements RwStrategy {
	private Object o;

	private final Object rLock = new Object();
	@GuardedBy("rLock")
	private int rCnt = 0;

	private void wait(Object o) {
		try {
			o.wait();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void write(Object o) {
		synchronized (rLock) {
//			ThreadHelper.log("try to write. rCnt="+rCnt);
			while (rCnt > 0) {
				wait(rLock);
			}
//			ThreadHelper.log("write");
			ThreadHelper.doIt();
			this.o = o;
			rLock.notify(); // notify next writer
		}
	}

	@Override
	public Object read() {
		synchronized (rLock) {
			rCnt++;
//			ThreadHelper.log("read. rCnt="+rCnt);
		}

		ThreadHelper.doIt();
		Object result = this.o;

		synchronized (rLock) {
			rCnt--;
//			ThreadHelper.log("read finished. rCnt="+rCnt);
			if (rCnt == 0) {
				rLock.notify(); // notify next writer
			}
		}

		return result;
	}
}