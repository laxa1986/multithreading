package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.FixedScenario;
import laxa.multithreading.framework.characteristics.BestStrategy;
import laxa.multithreading.task.readwrite.action.R;
import laxa.multithreading.task.readwrite.action.W;
import laxa.multithreading.task.readwrite.strategy.RwStrategy;
import laxa.multithreading.task.readwrite.strategy.T03_StrategyWrite;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
@BestStrategy(T03_StrategyWrite.class)
public class S07_R$WRW_R extends FixedScenario<RwStrategy> {
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