package laxa.multithreading.readwrite.strategy;

import laxa.multithreading.readwrite.characteristics.*;
import laxa.multithreading.readwrite.framework.ThreadHelper;

/**
 * Author: Chekulaev Alexey
 * Date: 18.01.2012
 */
@Throughput(Throughput.Value.MIN)
@WriteOrder(Fairness.UNFAIR_THREAD_PRIORITIES)
@Case_R$WR(fairness = Fairness.UNFAIR_THREAD_PRIORITIES, value = "next will be ? (both R & W wait on synchronized method)")
@Case_W$RW(fairness = Fairness.UNFAIR_THREAD_PRIORITIES, value = "next will be ? (both R & W wait on synchronized method)")
public class T01_Synchronized implements Strategy {
	private Object o;

	@Override
	public String getName() {
		return "[Sync   ]";
	}

	public synchronized void write(Object o) {
		ThreadHelper.doIt();
		this.o = o;
	}

	public synchronized Object read() {
		ThreadHelper.doIt();
		return o;
	}
}