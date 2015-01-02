package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.task.moneytransfer.model.Account;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
class LockHolder {
    // TODO: try Map<Long, ReadWriteLock>
    private final Map<Account, ReadWriteLock> lockMap = new WeakHashMap<>();

    /**
     * Thread safe lazy lock creation
     * @param account account
     * @return lock to get access
     */
    ReadWriteLock getLock(Account account) {
        ReadWriteLock lock = lockMap.get(account);
        if (lock == null) {
            synchronized (lockMap) {
                lock = lockMap.get(account);
                if (lock == null) {
                    lock = new ReentrantReadWriteLock();
                    lockMap.put(account, lock);
                }
            }
        }
        return lock;
    }
}