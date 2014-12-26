package laxa.multithreading.readwrite.action;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public interface Action {
	boolean isRead();
	int start();
	int duration();
}