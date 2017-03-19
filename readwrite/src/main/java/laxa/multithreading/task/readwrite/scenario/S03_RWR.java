package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.FixedScenario;
import laxa.multithreading.framework.characteristics.BestStrategy;
import laxa.multithreading.task.readwrite.action.R;
import laxa.multithreading.task.readwrite.action.W;
import laxa.multithreading.task.readwrite.strategy.RwStrategy;
import laxa.multithreading.task.readwrite.strategy.T02_StrategyRead;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
@BestStrategy(T02_StrategyRead.class)
public class S03_RWR extends FixedScenario<RwStrategy> {
	@Override
	public int getFastest() {
		return 100 + 50 + 100;
	}

	@Override
	public Action[] getActions() {
		return new Action[]{
				new R(0),
				new W(25),
				new R(50)
		};
	}
}