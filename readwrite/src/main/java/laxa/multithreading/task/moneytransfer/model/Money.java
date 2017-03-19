package laxa.multithreading.task.moneytransfer.model;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Author: Chekulaev Alexey
 * Date: 30.12.2014
 */
@Immutable
@NotThreadSafe
public class Money {
    private final double value;

    public Money(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Money add(Money money) {
        return new Money(value + money.getValue());
    }

    public Money sub(Money money) {
        return new Money(value - money.getValue());
    }
}