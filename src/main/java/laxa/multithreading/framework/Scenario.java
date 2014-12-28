package laxa.multithreading.framework;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public interface Scenario {
	int getFastest();
	
	Action[] getActions();
}