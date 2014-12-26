package laxa.multithreading.readwrite.scenario;

import laxa.multithreading.readwrite.action.Action;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public interface Scenario {
	int getFastest();
	
	Action[] getActions();
}