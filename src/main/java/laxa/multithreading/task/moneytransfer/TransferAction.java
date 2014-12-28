package laxa.multithreading.task.moneytransfer;

import laxa.multithreading.framework.Action;

/**
 * Author: Chekulaev Alexey
 * Date: 26.12.2014
 */
public class TransferAction implements Action {
    @Override
    public boolean isRead() {
        return false;
    }

    @Override
    public int getStartDelay() {
        return 0;
    }

    @Override
    public int duration() {
        return 0;
    }
}