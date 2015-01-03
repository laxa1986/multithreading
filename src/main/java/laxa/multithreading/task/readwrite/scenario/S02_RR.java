package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.FixedScenario;
import laxa.multithreading.task.readwrite.action.R;
import laxa.multithreading.task.readwrite.strategy.RwStrategy;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
public class S02_RR extends FixedScenario<RwStrategy> {
	@Override
	public int getFastest() {
		return 100;
	}

	@Override
	public Action[] getActions() {
		return new Action[]{
				new R(0),
				new R(0)
		};
	}
}