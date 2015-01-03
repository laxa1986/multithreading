package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.FixedScenario;
import laxa.multithreading.framework.characteristics.BestStrategy;
import laxa.multithreading.task.readwrite.action.R;
import laxa.multithreading.task.readwrite.action.W;
import laxa.multithreading.task.readwrite.strategy.RwStrategy;
import laxa.multithreading.task.readwrite.strategy.T02_StrategyRead;
import laxa.multithreading.task.readwrite.strategy.T03_StrategyWrite;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
@BestStrategy({T02_StrategyRead.class, T03_StrategyWrite.class})
public class S04_W$RW_R extends FixedScenario<RwStrategy> {
	@Override
	public int getFastest() {
		return 300;
	}

	@Override
	public Action[] getActions() {
		return new Action[]{
				new W(0),
				new R(20),
				new W(50),
				new R(150),
		};
	}
}