package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.FixedScenario;
import laxa.multithreading.framework.characteristics.BestStrategy;
import laxa.multithreading.task.readwrite.action.R;
import laxa.multithreading.task.readwrite.action.W;
import laxa.multithreading.task.readwrite.strategy.RwStrategy;
import laxa.multithreading.task.readwrite.strategy.T03_StrategyWrite;
import laxa.multithreading.task.readwrite.strategy.T04_StrategyFair;
import laxa.multithreading.task.readwrite.strategy.T06_RWLock;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
@BestStrategy({T06_RWLock.class, T03_StrategyWrite.class, T04_StrategyFair.class})
public class S05_W$WR_R extends FixedScenario<RwStrategy> {
	@Override
	public int getFastest() {
		return 300;
	}

	@Override
	public Action[] getActions() {
		return new Action[]{
				new W(0),
				new W(20),
				new R(50),
				new R(150),
		};
	}
}