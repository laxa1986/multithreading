package laxa.multithreading.readwrite.action;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public abstract class ActionImpl implements Action {
	private int start;
	private int duration;
	
	public ActionImpl(int start, int duration) {
		this.start = start;
		this.duration = duration;
	}
	
	@Override
	public int start() {
		return start;
	}

	@Override
	public int duration() {
		return duration;
	}
}