package laxa.multithreading.task.readwrite.strategy;

import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.locks.StampedLock;

/**
 * Author: Chekulaev Alexey
 * Date: 03.01.2015
 */
public class T09_StampedLock implements RwStrategy {
    private final StampedLock lock = new StampedLock();

    @GuardedBy("lock")
    private Object o;

    @Override
    public void write(Object o) {
        long stamp = lock.writeLock();
        try {
            this.o = o;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Override
    public Object read() {
        long stamp = lock.readLock();
        lock.tryOptimisticRead();
        try {
            return this.o;
        } finally {
            lock.unlockRead(stamp);
        }
    }
}