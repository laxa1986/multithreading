package laxa.multithreading.readwrite.strategy;

import laxa.multithreading.readwrite.characteristics.*;
import laxa.multithreading.readwrite.framework.ThreadHelper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: Chekulaev Alexey
 * Date: 26.02.12
 *
 * - �������� ���������� �������� ������������
 * - ����������� �������� ����������
 * - ���������� ��������� ���������
 * - ������������� ���������� �������
 * - ����� � ���������� (lock polling)
 * - reentrantLock ����� �������� �������������� ���������������� (����� �����������/����)
 */
@Throughput(Throughput.Value.MIDDLE)
@WriteOrder(Fairness.UNFAIR_READ_OPTIMIZATION)
@Case_R$WR(fairness = Fairness.FAIR, value = "second reader will wait")
@Case_W$RW(fairness = Fairness.UNDEFINED, value = "")
public class T06_RWLock implements Strategy {
	@Override
	public String getName() {
		return "[RW Lock]";
	}

	private ReadWriteLock lock = new ReentrantReadWriteLock(false /* not fair */);
	private Object o;

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