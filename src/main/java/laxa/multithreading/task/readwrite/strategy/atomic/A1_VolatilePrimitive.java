package laxa.multithreading.task.readwrite.strategy.atomic;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class A1_VolatilePrimitive {
	private volatile int o;
//	byte o;
//	boolean o;
//	char o;
//	short o;
//	float o;

	public void write(int o) {
		this.o = o;
	}

	public int read() {
		return o;
	}
}