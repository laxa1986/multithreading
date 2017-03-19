package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.AccountLocks;
import laxa.multithreading.task.moneytransfer.model.Money;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class T03_LockTimeout2 implements TransferStrategy {
    private final AccountLocks locks = new AccountLocks();
    
    private final int tryLockMaxTime;

    /**
     * @param tryLockMaxTime max time to try acquire the lock
     */
    public T03_LockTimeout2(int tryLockMaxTime) {
        this.tryLockMaxTime = tryLockMaxTime;
    }

    @Override
    public void transfer(Account from, Account to, Money amount) {
        StampedLock lockFrom = locks.getStampedLock(from);
        StampedLock lockTo = locks.getStampedLock(to);
        Random random = ThreadLocalRandom.current();

        long stamp = lockFrom.writeLock();
        try {
            long lockedTo;
            try {
                lockedTo = lockTo.tryWriteLock(random.nextInt(tryLockMaxTime), TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                return;
            }
            if (lockedTo != 0) {
                try {
                    from.transfer(to, amount);
                } finally {
                    lockTo.unlockWrite(stamp);
                }
            } else {
                // need special method: unlock write without changes
                lockFrom.unlockWrite(stamp);
                lockFrom = null;

                // try again
                transfer(from, to, amount);
            }
        } finally {
            if (lockFrom != null) {
                lockFrom.unlockWrite(stamp);
            }
        }
    }
    
    private Money lockGetMoney(Account account, StampedLock lock) {
        long stamp = lock.readLock();
        try {
            return account.getMoney();
        } finally {
            lock.unlockRead(stamp);
        }
    }

    @Override
    public Money getMoney(Account account) {
        StampedLock lock = locks.getStampedLock(account);
        long stamp = lock.tryOptimisticRead();

        Money money;
        if (stamp == 0) {
            money = lockGetMoney(account, lock);
        } else {
            money = account.getMoney();
            if (!lock.validate(stamp)) {
                money = lockGetMoney(account, lock);
            }
        }
        return money;
    }
}