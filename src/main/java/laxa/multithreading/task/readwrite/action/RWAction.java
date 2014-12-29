package laxa.multithreading.task.readwrite.action;

import laxa.multithreading.framework.FixedScenario;
import laxa.multithreading.task.readwrite.strategy.RwStrategy;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public abstract class RWAction implements FixedScenario.Action<RwStrategy> {
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

	protected abstract boolean isRead();

	@Override
	public void run(RwStrategy strategy) {
		if (isRead()) {
			strategy.read();
		} else {
			strategy.write(new Object());
		}
	}

	@Override
	public int duration() {
		return duration;
	}
}