package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.Money;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Lock;

/**
 * Use additional monitor 'monitor' to exclude deadlocks while trying to get two write locks
 *
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
@ThreadSafe
public class T04_MonitorLock implements TransferStrategy {
    private final Object monitor = new Object();
    private final LockHolder lockHolder = new LockHolder();

    @Override
    public void transfer(Account from, Account to, Money amount) {
        Lock lockFrom = lockHolder.getLock(from).writeLock();
        Lock lockTo = lockHolder.getLock(to).writeLock();

        // exclude deadlocks
        synchronized (monitor) {
            while (true) {
                if (lockFrom.tryLock()) {
                    if (lockTo.tryLock()) {
                        break;
                    } else {
                        lockFrom.unlock();
                    }
                }
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        try {
            from.setMoney(from.getMoney().sub(amount));
        } catch (RuntimeException e) {
            lockTo.unlock();
            throw e;
        } finally {
            lockFrom.unlock();
            synchronized (monitor) {
                monitor.notifyAll(); // will notify to many
            }
        }

        try {
            to.setMoney(to.getMoney().add(amount));
        } finally {
            lockTo.unlock();
            synchronized (monitor) {
                monitor.notifyAll(); // will notify to many
            }
        }
    }

    @Override
    public Money getMoney(Account account) {
        Lock lock = lockHolder.getLock(account).readLock();

        synchronized (monitor) {
            while (true) {
                if (lock.tryLock()) {
                    break;
                }
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }

        try {
            return account.getMoney();
        } finally {
            lock.unlock();
            synchronized (monitor) {
                monitor.notifyAll(); // will notify to many
            }
        }
    }
}