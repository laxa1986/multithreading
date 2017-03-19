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
    private final Scenario[] scenarios = new Scenario[]{new S02_RR(), new S03_RWR(), new S04_W$RW_R(), new S05_W$WR_R(), new S06_R$WR_R(), new S07_R$WRW_R()};

    private final RwStrategy[] strategies = new RwStrategy[]{new T01_Synchronized(), new T02_StrategyRead(), new T03_StrategyWrite(), new T04_StrategyFair(), new T05_ReentrantLock(), new T06_RWLock(true/*fair*/), new T08_CustomLock(true/*fair*/)};

    @Test
    public void testT03_StrategyWrite() {
        new TestRunner().testStrategy(scenarios, new T03_StrategyWrite());
    }

    @Test
    public void testWriteScenarios() {
        new TestRunner().testScenario(new S03_RWR(), strategies);
        new TestRunner().testScenario(new S04_W$RW_R(), strategies);
    }

    @Test
    @Ignore
    public void testAll() {
        new TestRunner().testStrategy(scenarios, new T01_Synchronized());
        new TestRunner().testStrategy(scenarios, new T02_StrategyRead());
        new TestRunner().testStrategy(scenarios, new T03_StrategyWrite());
        new TestRunner().testStrategy(scenarios, new T04_StrategyFair());
        new TestRunner().testStrategy(scenarios, new T05_ReentrantLock());
        new TestRunner().testStrategy(scenarios, new T06_RWLock(true/*fair*/));
        new TestRunner().testStrategy(scenarios, new T08_CustomLock(true/*fair*/));
    }
}