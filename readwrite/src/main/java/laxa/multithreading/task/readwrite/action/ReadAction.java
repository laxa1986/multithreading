package laxa.multithreading.task.readwrite.action;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public class ReadAction extends RWAction {
	public ReadAction(int start, int duration) {
		super(start, duration);
	}

	@Override
	protected final boolean isRead() {
		return true;
	}
}