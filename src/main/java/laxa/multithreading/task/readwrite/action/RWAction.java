package laxa.multithreading.task.readwrite.action;

import laxa.multithreading.framework.Action;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public abstract class RWAction implements Action {
	private int start;
	private int duration;
	
	public RWAction(int start, int duration) {
		this.start = start;
		this.duration = duration;
	}
	
	@Override
	public int getStartDelay() {
		return start;
	}

	@Override
	public int duration() {
		return duration;
	}
}