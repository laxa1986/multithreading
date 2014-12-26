package laxa.multithreading.readwrite.action;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public class ReadAction extends ActionImpl {
	public ReadAction(int start, int duration) {
		super(start, duration);
	}

	@Override
	public boolean isRead() {
		return true;
	}
}