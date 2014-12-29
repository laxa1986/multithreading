package laxa.multithreading.task.readwrite;

import laxa.multithreading.framework.Scenario;
import laxa.multithreading.framework.TestRunner;
import laxa.multithreading.task.readwrite.scenario.*;
import laxa.multithreading.task.readwrite.strategy.*;
import org.junit.Test;

/**
 * Author: Chekulaev Alexey
 * Date: 26.12.2014
 */
public class ReadWriteTest {
    private final Scenario[] scenarios = new Scenario[]{new C02_RR(), new C03_RWR(), new C04_W$RW_R(), new C05_W$WR_R(), new C06_R$WR_R(), new C07_R$WRW_R()};

    private static void CR() {
        System.out.println();
    }

    @Test
    public void testAll() throws Exception {
        new TestRunner(new T01_Synchronized()).test(scenarios);
        new TestRunner(new T02_StrategyRead()).test(scenarios);
        new TestRunner(new T03_StrategyWrite()).test(scenarios);
        new TestRunner(new T04_StrategyFair()).test(scenarios);
        new TestRunner(new T05_ReentrantLock()).test(scenarios);
        new TestRunner(new T06_RWLock()).test(scenarios);
        new TestRunner(new T07_RWLockFair()).test(scenarios);
        new TestRunner(new T08_CustomLock()).test(scenarios);
    }
}