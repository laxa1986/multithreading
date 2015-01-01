package laxa.multithreading.task.readwrite;

import laxa.multithreading.framework.Scenario;
import laxa.multithreading.framework.TestRunner;
import laxa.multithreading.task.readwrite.scenario.*;
import laxa.multithreading.task.readwrite.strategy.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Author: Chekulaev Alexey
 * Date: 26.12.2014
 */
public class ReadWriteTest {
    private final Scenario[] scenarios = new Scenario[]{new C02_RR(), new C03_RWR(), new C04_W$RW_R(), new C05_W$WR_R(), new C06_R$WR_R(), new C07_R$WRW_R()};

    private final RwStrategy[] strategies = new RwStrategy[]{new T01_Synchronized(), new T02_StrategyRead(), new T03_StrategyWrite(), new T04_StrategyFair(), new T05_ReentrantLock(), new T06_RWLock(), new T07_RWLockFair(), new T08_CustomLock()};

    @Test
    public void testT03_StrategyWrite() {
        new TestRunner().testStrategy(scenarios, new T03_StrategyWrite());
    }

    @Test
    public void testWriteScenarios() {
        new TestRunner().testScenario(new C03_RWR(), strategies);
        new TestRunner().testScenario(new C04_W$RW_R(), strategies);
    }

    @Test
    @Ignore
    public void testAll() {
        new TestRunner().testStrategy(scenarios, new T01_Synchronized());
        new TestRunner().testStrategy(scenarios, new T02_StrategyRead());
        new TestRunner().testStrategy(scenarios, new T03_StrategyWrite());
        new TestRunner().testStrategy(scenarios, new T04_StrategyFair());
        new TestRunner().testStrategy(scenarios, new T05_ReentrantLock());
        new TestRunner().testStrategy(scenarios, new T06_RWLock());
        new TestRunner().testStrategy(scenarios, new T07_RWLockFair());
        new TestRunner().testStrategy(scenarios, new T08_CustomLock());
    }
}