package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.Action;
import laxa.multithreading.framework.Scenario;
import laxa.multithreading.task.readwrite.action.WriteAction;

/**
 * Author: Chekulaev Alexey
 * Date: 11.03.12
 */
public class SimpleActionsScenario implements Scenario {
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