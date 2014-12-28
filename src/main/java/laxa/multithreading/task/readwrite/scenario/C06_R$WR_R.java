package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.Action;
import laxa.multithreading.framework.Scenario;
import laxa.multithreading.framework.characteristics.BestStrategy;
import laxa.multithreading.task.readwrite.action.R;
import laxa.multithreading.task.readwrite.action.W;
import laxa.multithreading.task.readwrite.strategy.T03_StrategyWrite;
import laxa.multithreading.task.readwrite.strategy.T04_StrategyFair;
import laxa.multithreading.task.readwrite.strategy.T06_RWLock;
import laxa.multithreading.task.readwrite.strategy.T07_RWLockFair;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
@BestStrategy({T06_RWLock.class, T03_StrategyWrite.class, T04_StrategyFair.class, T07_RWLockFair.class})
public class C06_R$WR_R implements Scenario {
	@Override
	public int getFastest() {
		return 300;
	}

	@Override
	public Action[] getActions() {
		return new Action[]{
				new R(0),
				new W(20),
				new R(80),
				new R(160)
		};
	}
}