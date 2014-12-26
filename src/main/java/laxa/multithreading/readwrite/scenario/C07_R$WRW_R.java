package laxa.multithreading.readwrite.scenario;

import laxa.multithreading.readwrite.action.Action;
import laxa.multithreading.readwrite.action.R;
import laxa.multithreading.readwrite.action.W;
import laxa.multithreading.readwrite.characteristics.BestStrategy;
import laxa.multithreading.readwrite.strategy.T03_StrategyWrite;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
@BestStrategy(T03_StrategyWrite.class)
public class C07_R$WRW_R implements Scenario {
	@Override
	public int getFastest() {
		return 400;
	}

	@Override
	public Action[] getActions() {
		return new Action[]{
				new R(0),
				
				new W(20),
				new R(40),
				new W(60),
				
				new R(160)
		};
	}
}