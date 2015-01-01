package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.framework.characteristics.Fairness;
import laxa.multithreading.framework.characteristics.WriteOrder;
import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.Money;

import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: Chekulaev Alexey
 * Date: 30.12.2014
 */
@WriteOrder(Fairness.UNFAIR)
public class T02_LockTimeout implements TransferStrategy {
    private final Map<Account, ReadWriteLock> lockMap = new WeakHashMap<>();
    private final Random random = new Random(System.currentTimeMillis());

    private final int tryLockMaxTime;

    /**
     * @param tryLockMaxTime max time to try acquire the lock
     */
    public T02_LockTimeout(int tryLockMaxTime) {
        this.tryLockMaxTime = tryLockMaxTime;
    }

    /**
     * Thread safe lazy lock creation
     * @param account account
     * @return lock to get access
     */
    private ReadWriteLock getLock(Account account) {
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

    @Override
    public void transfer(Account from, Account to, Money amount) {
        Lock lockFrom = getLock(from).writeLock();
        Lock lockTo = getLock(to).writeLock();

        lockFrom.lock();
        try {
            boolean lockedTo;
            try {
                lockedTo = lockTo.tryLock(random.nextInt(tryLockMaxTime), TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                return;
            }
            if (lockedTo) {
                try {
                    from.transfer(to, amount);
                } finally {
                    lockTo.unlock();
                }
            } else {
                lockFrom.unlock();
                lockFrom = null;

                // try again
                transfer(from, to, amount);
            }
        } finally {
            if (lockFrom != null) {
                lockFrom.unlock();
            }
        }
    }

    @Override
    public Money getMoney(Account account) {
        Lock lock = getLock(account).readLock();
        lock.lock();
        try {
            return account.getMoney();
        } finally {
            lock.unlock();
        }
    }
}