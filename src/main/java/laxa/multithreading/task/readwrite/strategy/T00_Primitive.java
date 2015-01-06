package laxa.multithreading.task.readwrite.strategy;

/**
 * Author: Chekulaev Alexey
 * Date: 29.01.12
 */
public class T00_Primitive {
	private int o;
//	byte o;
//	boolean o;
//	char o;
//	short o;
//	float o;
//  Object o; // Writes to and reads of references are always atomic, regardless of whether they are implemented as 32 or 64 bit values

	// except long and double (use AtomicLong and AtomicDouble)
	
	public void write(int o) {
		this.o = o;
	}

	public int read() {
		return o;
	}
}