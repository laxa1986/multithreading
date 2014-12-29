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
        new TestRunner().test(scenarios, new T01_Synchronized());
        new TestRunner().test(scenarios, new T02_StrategyRead());
        new TestRunner().test(scenarios, new T03_StrategyWrite());
        new TestRunner().test(scenarios, new T04_StrategyFair());
        new TestRunner().test(scenarios, new T05_ReentrantLock());
        new TestRunner().test(scenarios, new T06_RWLock());
        new TestRunner().test(scenarios, new T07_RWLockFair());
        new TestRunner().test(scenarios, new T08_CustomLock());
    }
}