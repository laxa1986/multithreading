package laxa.multithreading.task.moneytransfer.strategy;

import laxa.multithreading.framework.Strategy;
import laxa.multithreading.task.moneytransfer.model.Account;
import laxa.multithreading.task.moneytransfer.model.Money;

/**
 * Author: Chekulaev Alexey
 * Date: 26.12.2014
 */
public interface TransferStrategy extends Strategy {
    void transfer(Account from, Account to, Money amount);

    /**
     * In all implementations any synchronization can be skipped because method {@link laxa.multithreading.task.moneytransfer.model.Account#getMoney()}
     * just return a reference field (reading references is atomic operation) and Money itself is immutable
     *
     * @param account account
     * @return money
     */
    default Money getMoney(Account account) {
        return account.getMoney();
    }
}