package laxa.multithreading.framework;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public interface Action {
	boolean isRead();

	int getStartDelay();

	int duration();
}