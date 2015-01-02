package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.Money;

/**
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
public class T02_Sync implements TransferStrategy {
    @Override
    public void transfer(Account from, Account to, Money amount) {
        synchronized (from) {
            from.setMoney(from.getMoney().sub(amount));
        }

        synchronized (to) {
            to.setMoney(to.getMoney().add(amount));
        }
    }

    @Override
    public Money getMoney(Account account) {
        synchronized (account) {
            return account.getMoney();
        }
    }
}