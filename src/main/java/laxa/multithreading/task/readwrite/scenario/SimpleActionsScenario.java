package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.FixedScenario;
import laxa.multithreading.task.readwrite.action.WriteAction;
import laxa.multithreading.task.readwrite.strategy.RwStrategy;

/**
 * Author: Chekulaev Alexey
 * Date: 11.03.12
 */
public class SimpleActionsScenario extends FixedScenario<RwStrategy> {
	@Override
	public int getFastest() {
		return 0;
	}

	@Override
	public Action[] getActions() {
		Action[] actions = new Action[3000];
		for (int i=0; i<actions.length; i++) {
			Action action = new WriteAction(0, 0);
			actions[i] = action;
		}
		
		return actions;
	}
}