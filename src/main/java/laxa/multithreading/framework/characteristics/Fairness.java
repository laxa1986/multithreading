package laxa.multithreading.framework.characteristics;

/**
 * Author: Chekulaev Alexey
 * Date: 09.03.12
 */
public enum Fairness {
	UNDEFINED,

	/**
	 * Unfair. Depend on thread priorities, because of synchronized(monitor)
	 */
	UNFAIR_THREAD_PRIORITIES,

	/**
	 * Unfair special for optimization
	 */
	UNFAIR_READ_OPTIMIZATION,

	/**
	 * Fair
	 */
	FAIR
}