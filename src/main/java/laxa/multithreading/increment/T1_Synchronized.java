package laxa.multithreading.increment;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class T1_Synchronized implements IncTask {
	private int i = 0;
	
	public synchronized int next() {
		i++;
		return i;
	}
}