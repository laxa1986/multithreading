package laxa.multithreading.task.readwrite.action;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public class WriteAction extends RWAction {
	public WriteAction(int start, int duration) {
		super(start, duration);
	}

	@Override
	public boolean isRead() {
		return false;
	}
}