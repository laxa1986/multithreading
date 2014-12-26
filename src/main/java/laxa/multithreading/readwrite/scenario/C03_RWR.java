package laxa.multithreading.readwrite.scenario;

import laxa.multithreading.readwrite.action.Action;
import laxa.multithreading.readwrite.action.R;
import laxa.multithreading.readwrite.action.W;
import laxa.multithreading.readwrite.characteristics.BestStrategy;
import laxa.multithreading.readwrite.strategy.T02_StrategyRead;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
@BestStrategy(T02_StrategyRead.class)
public class C03_RWR implements Scenario {
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