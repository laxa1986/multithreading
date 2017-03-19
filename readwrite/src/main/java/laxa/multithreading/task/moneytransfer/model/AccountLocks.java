package laxa.multithreading.task.moneytransfer.model;

import laxa.multithreading.framework.utils.LazyFactory;

import javax.annotation.Nonnull;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class AccountLocks {
    private final LazyFactory<Account, ReadWriteLock> factory = new LazyFactory<Account, ReadWriteLock>() {
        @Override
        protected @Nonnull ReadWriteLock create() {
            return new ReentrantReadWriteLock();
        }
    };

    private final LazyFactory<Account, StampedLock> factory2 = new LazyFactory<Account, StampedLock>() {
        @Override
        protected @Nonnull StampedLock create() {
            return new StampedLock();
        }
    };

    /**
     * Thread safe lazy lock creation
     *
     * @param account account id
     * @return lock to get access
     */
    public @Nonnull ReadWriteLock getRWLock(@Nonnull Account account) {
        return factory.get(account);
    }

    public @Nonnull StampedLock getStampedLock(@Nonnull Account account) {
        return factory2.get(account);
    }
}