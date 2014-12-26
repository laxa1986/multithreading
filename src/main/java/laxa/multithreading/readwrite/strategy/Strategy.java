package laxa.multithreading.readwrite.strategy;

public interface Strategy {
	String getName();

	void write(Object o);

	Object read();
}