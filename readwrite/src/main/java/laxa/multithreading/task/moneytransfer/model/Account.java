package laxa.multithreading.task.moneytransfer.model;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Author: Chekulaev Alexey
 * Date: 30.12.2014
 */
@NotThreadSafe
public class Account {
    private final long id;
    private Money money = new Money(0);

    public Account(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    /**
     * ThreadSafe as reading/writing references are atomic operation and Money itself is immutable
     * @return money
     */
    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void transfer(Account to, Money amount) {
        this.setMoney(this.getMoney().sub(amount));
        to.setMoney(to.getMoney().add(amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}