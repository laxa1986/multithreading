package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.Money;

/**
 * Author: Chekulaev Alexey
 * Date: 30.12.2014
 */
public class T01_Exclusive implements TransferStrategy {
    private static final Object lock = new Object();

    @Override
    public void transfer(Account from, Account to, Money amount) {
        synchronized (lock) {
            from.transfer(to, amount);
        }
    }

    @Override
    public Money getMoney(Account account) {
        synchronized (lock) {
            return account.getMoney();
        }
    }
}