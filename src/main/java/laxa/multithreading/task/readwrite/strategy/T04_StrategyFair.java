package laxa.multithreading.task.readwrite.strategy;

import laxa.multithreading.framework.ThreadHelper;
import laxa.multithreading.framework.characteristics.*;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: Chekulaev Alexey
 * Date: 04.03.12
 */
@ThreadSafe
@Throughput(Throughput.Value.MIDDLE)
@WriteOrder(Fairness.FAIR)
@Case_R$WR(fairness = Fairness.FAIR, value = "second reader will wait")
@Case_W$RW(fairness = Fairness.FAIR, value = "next will be reader")
public class T04_StrategyFair implements RwStrategy {
	private Object o;

	private static interface Monitor {}

	private static final class ReadMonitor implements Monitor {
		int cnt = 1;
	}
	
	private static final class WriteMonitor implements Monitor {
	}
	
	private static final class MyQueue<T> {
		private Deque<T> deque = new ArrayDeque<T>();

		private synchronized boolean isFirst(T monitor) {
			return deque.getFirst() == monitor;
		}
		
		// delegate methods
		
		private void offer(T monitor) {
			deque.offer(monitor);
		}
		
		private void remove() {
			deque.remove();
		}
		
		private boolean isEmpty() {
			return deque.isEmpty();
		}
		
		private T getFirst() {
			return deque.getFirst();
		}
		
		private T peekLast() {
			return deque.peekLast();
		}
	}
	
	private final MyQueue<Monitor> queue = new MyQueue<Monitor>();

	private void wait(Object o) {
		try {
			o.wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String logQueue() {
		synchronized (queue) {
			StringBuilder loggedQueue = new StringBuilder();
			for (Monitor monitor : queue.deque) {
				if (monitor instanceof ReadMonitor) {
					loggedQueue.append("r|");
				} else {
					loggedQueue.append("w|");
				}
			}
			if (loggedQueue.length()>0) {
				return " > " + loggedQueue.substring(0, loggedQueue.length()-1);
			} else {
				return "";
			}
		}
	}

	private void processNext() {
		queue.remove(); // remove itself
		if (!queue.isEmpty()) {
			Monitor monitor = queue.getFirst();
			synchronized (monitor) {
				monitor.notifyAll();
			}
		}
	}

	@Override
	public void write(Object o) {
//		ThreadHelper.log("enter write");
		// TODO: check!!!
		WriteMonitor wMonitor = new WriteMonitor();
		synchronized (queue) {
			queue.offer(wMonitor);
		}

		while (!queue.isFirst(wMonitor)) {
			synchronized (wMonitor) {
				wait(wMonitor);
			}
		}

//		ThreadHelper.log("write " + logQueue());
		ThreadHelper.doIt();
		this.o = o;
		
		synchronized (queue) {		
			processNext();
		}
	}

	@Override
	public Object read() {
//		ThreadHelper.log("enter read");
		ReadMonitor rMonitor;
		synchronized (queue) {
			Monitor monitor = queue.peekLast();
			if (monitor instanceof ReadMonitor) {
				rMonitor = (ReadMonitor)monitor;
//				ThreadHelper.log("get existing rMonitor. cnt: " + rMonitor.cnt);
				rMonitor.cnt++;
			} else { // WriteMonitor or queue is empty
//				ThreadHelper.log("create rMonitor cnt: 1");
				rMonitor = new ReadMonitor();
				queue.offer(rMonitor);
			}
		}

		while (!queue.isFirst(rMonitor)) {
			synchronized (rMonitor) {
				wait(rMonitor);
			}
		}

//		ThreadHelper.log("read " + logQueue());
		ThreadHelper.doIt();
		Object result = this.o;

		synchronized (queue) {
			rMonitor.cnt--;
//			ThreadHelper.log("read cnt: " + rMonitor.cnt);
			if (rMonitor.cnt == 0) {
				processNext();
			}
		}

		return result;
	}
}