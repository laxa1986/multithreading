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
@Throughput(Throughput.Value.MIN)
@WriteOrder(Fairness.UNFAIR_THREAD_PRIORITIES)
@Case_R$WR(fairness = Fairness.UNFAIR_THREAD_PRIORITIES, value = "next will be ? (both R & W wait on synchronized method)")
@Case_W$RW(fairness = Fairness.UNFAIR_THREAD_PRIORITIES, value = "next will be ? (both R & W wait on synchronized method)")
public class T01_Synchronized implements RwStrategy {
	@GuardedBy("this")
	private Object o;

	@Override
	public synchronized void write(Object o) {
		ThreadHelper.doIt();
		this.o = o;
	}

	@Override
	public synchronized Object read() {
		ThreadHelper.doIt();
		return o;
	}
}