package laxa.multithreading.framework.utils;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Author: Chekulaev Alexey
 * Date: 02.01.2015
 */
@ThreadSafe
public abstract class LazyFactory<K, V> {
    private final Map<K, V> lockMap = new WeakHashMap<>();

    /**
     * Thread safe lazy lock creation
     *
     * @param account account id
     * @return lock to get access
     */
    public @Nonnull V get(@Nonnull K account) {
        V lock = lockMap.get(account);
        if (lock == null) {
            synchronized (lockMap) {
                lock = lockMap.get(account);
                if (lock == null) {
                    lock = create();
                    lockMap.put(account, lock);
                }
            }
        }
        return lock;
    }

    protected abstract  @Nonnull V create();
}