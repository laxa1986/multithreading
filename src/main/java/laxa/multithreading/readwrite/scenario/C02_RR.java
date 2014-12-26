package laxa.multithreading.readwrite.scenario;

import laxa.multithreading.readwrite.action.Action;
import laxa.multithreading.readwrite.action.R;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
public class C02_RR implements Scenario {
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