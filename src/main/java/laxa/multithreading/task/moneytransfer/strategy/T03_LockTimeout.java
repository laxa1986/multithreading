package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.framework.characteristics.Fairness;
import laxa.multithreading.framework.characteristics.WriteOrder;
import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.AccountLocks;
import laxa.multithreading.task.moneytransfer.model.Money;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Author: Chekulaev Alexey
 * Date: 30.12.2014
 */
@ThreadSafe
@WriteOrder(Fairness.UNFAIR)
public class T03_LockTimeout implements TransferStrategy {
    private final AccountLocks locks = new AccountLocks();
    private final int tryLockMaxTime;

    /**
     * @param tryLockMaxTime max time to try acquire the lock
     */
    public T03_LockTimeout(int tryLockMaxTime) {
        this.tryLockMaxTime = tryLockMaxTime;
    }

    @Override
    public void transfer(Account from, Account to, Money amount) {
        Lock lockFrom = locks.getRWLock(from).writeLock();
        Lock lockTo = locks.getRWLock(to).writeLock();
        Random random = ThreadLocalRandom.current();

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
        Lock lock = locks.getRWLock(account).readLock();
        lock.lock();
        try {
            return account.getMoney();
        } finally {
            lock.unlock();
        }
    }
}