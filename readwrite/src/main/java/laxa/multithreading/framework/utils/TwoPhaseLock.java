package laxa.multithreading.framework.utils;

import java.util.concurrent.locks.StampedLock;

/**
 * After acquire write lock it's required to confirm that you will modify the data
 * If write lock acquired but not confirmed then validate should still return true (cause data didn't changed)
 * 
 * NOT FINISHED
 */
public class TwoPhaseLock extends StampedLock {
    private boolean confirmed = false;

    @Override
    public long writeLock() {
        long stamp = super.writeLock();
        confirmed = false;
        return stamp;
    }

    public void confirm() {
        confirmed = true;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @Override
    public void unlockWrite(long stamp) {
        super.unlockWrite(stamp);
        confirmed = false; // TODO: check the sequence
    }
}