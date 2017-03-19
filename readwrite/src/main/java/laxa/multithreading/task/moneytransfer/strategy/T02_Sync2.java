package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.AccountLocks;
import laxa.multithreading.task.moneytransfer.model.Money;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Lock;

/**
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
@ThreadSafe
public class T02_Sync2 implements TransferStrategy {
    private final AccountLocks locks = new AccountLocks();

    @Override
    public void transfer(Account from, Account to, Money amount) {
        Lock fromLock = locks.getRWLock(from).writeLock();
        fromLock.lock();
        try {
            from.setMoney(from.getMoney().sub(amount));
        } finally {
            fromLock.unlock();
        }

        Lock toLock = locks.getRWLock(to).writeLock();
        toLock.lock();
        try {
            to.setMoney(to.getMoney().add(amount));
        } finally {
            toLock.unlock();
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