package laxa.multithreading.task.moneytransfer.strategy;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
class LockHolder {
    private final Map<Long, ReadWriteLock> lockMap = new WeakHashMap<>();

    /**
     * Thread safe lazy lock creation
     * @param accountId account id
     * @return lock to get access
     */
    ReadWriteLock getLock(long accountId) {
        ReadWriteLock lock = lockMap.get(accountId);
        if (lock == null) {
            synchronized (lockMap) {
                lock = lockMap.get(accountId);
                if (lock == null) {
                    lock = new ReentrantReadWriteLock();
                    // new Long need to make key eligible for GC
                    lockMap.put(new Long(accountId), lock);
                }
            }
        }
        return lock;
    }
}