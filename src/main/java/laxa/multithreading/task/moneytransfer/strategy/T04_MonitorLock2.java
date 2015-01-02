package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.Money;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Better throughput then original T04_MonitorLock
 *
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
public class T04_MonitorLock2 implements TransferStrategy {
    private final Object monitor = new Object();
    private LockHolder lockHolder = new LockHolder();

    private void unlockAndNotify(ReadWriteLock lock) {
        lock.writeLock().unlock();
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public void transfer(Account from, Account to, Money amount) {
        ReadWriteLock lockFrom = lockHolder.getLock(from);
        ReadWriteLock lockTo = lockHolder.getLock(to);
        Lock wLockFrom = lockFrom.writeLock();
        Lock wLockTo = lockTo.writeLock();


        // exclude deadlocks
        while (true) {
            boolean lockedFrom;

            synchronized (monitor) {
                lockedFrom = wLockFrom.tryLock();
                if (lockedFrom) {
                    if (wLockTo.tryLock()) {
                        break;
                    }
                }
            }

            if (lockedFrom) {
                unlockAndNotify(lockFrom);
                synchronized (lockTo) {
                    try {
                        lockTo.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            } else {
                synchronized (lockFrom) {
                    try {
                        lockFrom.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
            transfer(from, to, amount);
        }

        try {
            from.setMoney(from.getMoney().sub(amount));
        } catch (RuntimeException e) {
            unlockAndNotify(lockTo);
            throw e;
        } finally {
            unlockAndNotify(lockFrom);
        }

        try {
            to.setMoney(to.getMoney().add(amount));
        } finally {
            unlockAndNotify(lockTo);
        }
    }

    @Override
    public Money getMoney(Account account) {
        ReadWriteLock lock = lockHolder.getLock(account);
        Lock rLock = lock.readLock();
        rLock.lock();
        try {
            return account.getMoney();
        } finally {
            rLock.unlock();
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }
}