package laxa.multithreading.task.moneytransfer.model;

/**
 * Author: Chekulaev Alexey
 * Date: 30.12.2014
 */
public class Account {
    private final long id;
    private Money money = new Money(0);

    public Account(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}