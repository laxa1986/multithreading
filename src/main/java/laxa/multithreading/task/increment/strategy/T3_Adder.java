package laxa.multithreading.task.increment.strategy;

import java.util.concurrent.atomic.LongAdder;

public class T3_Adder implements IncrementStrategy {
    private final LongAdder adder = new LongAdder();
    
    @Override
    public int next() {
        adder.increment();
        return adder.intValue();
    }
}