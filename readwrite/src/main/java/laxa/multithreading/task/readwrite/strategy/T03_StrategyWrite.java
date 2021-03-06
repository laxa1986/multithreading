package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.ThreadHelper;
import laxa.multithreading.framework.characteristics.*;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Author: Chekulaev Alexey
 * Date: 20.01.2012
 */
@ThreadSafe
@Throughput(Throughput.Value.MAX)
@WriteOrder(Fairness.UNFAIR_READ_OPTIMIZATION)
@Case_R$WR(fairness = Fairness.FAIR, value = "second reader will wait")
@Case_W$RW(fairness = Fairness.UNFAIR_READ_OPTIMIZATION, value = "next will be writer")
public class T03_StrategyWrite implements RwStrategy {
	private Object o;

	private final Object wLock = new Object();
	@GuardedBy("wLock")
	private int wantW = 0;

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
		synchronized (wLock) {
			wantW++;
//			ThreadHelper.log("enter write. wantW="+ wantW);
		}

		synchronized (rLock) {
//			ThreadHelper.log("try to write. rCnt="+rCnt);
			
			while (rCnt > 0) {
				wait(rLock);
			}

//			ThreadHelper.log("write");
			ThreadHelper.doIt();
			this.o = o;
		}

		synchronized (wLock) {
			wantW--;
//			ThreadHelper.log("write finished. wantW="+ wantW);
			if (wantW == 0) {
				wLock.notifyAll(); // notify readers
			} else {
				synchronized (rLock) {
					rLock.notify(); // some writers wait for one reader. reader finished, notify one writer, then this writer notify another
				}
			}
		}
	}

	@Override
	public Object read() {
		synchronized (wLock) {
//			ThreadHelper.log("try to read. wantW="+ wantW);
			while (wantW > 0) {
				wait(wLock);
			}
			synchronized (rLock) {
				rCnt++;
//				ThreadHelper.log("can read. rCnt="+rCnt);
			}
		}

//		ThreadHelper.log("read");
		ThreadHelper.doIt();
		Object result = this.o;

		synchronized (rLock) {
			rCnt--;
//			ThreadHelper.log("read finished. rCnt="+rCnt);
			if (rCnt == 0) {
				rLock.notify();
			}
		}

		return result;
	}
}