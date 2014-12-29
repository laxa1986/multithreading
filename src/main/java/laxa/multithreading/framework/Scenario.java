package laxa.multithreading.framework;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public interface Scenario<S extends Strategy> {
	void run(S strategy);
}