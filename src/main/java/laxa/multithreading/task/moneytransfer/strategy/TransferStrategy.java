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
}