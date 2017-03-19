package laxa.multithreading.framework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: Chekulaev Alexey
 * Date: 30.12.2014
 */
public abstract class FixedScenario<S extends Strategy> implements Scenario<S> {
    public static interface Action<S> {
        int getStartDelay();

        void run(S strategy);

        int duration();
    }

    protected abstract int getFastest();

    protected abstract Action[] getActions();

    private CountDownLatch latch;

    @Override
    public void run(S strategy) {
        @SuppressWarnings("unchecked")
        Action<S>[] actions = getActions();

        ExecutorService executorService = Executors.newFixedThreadPool(actions.length);
        latch = new CountDownLatch(1);
        for (final Action<S> action : actions) {
            executorService.submit(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    return;
                }

                int startDelay = action.getStartDelay();
                if (startDelay > 0) {
                    ThreadHelper.sleep(startDelay);
                }
                ThreadHelper.setDuration(action.duration());
                action.run(strategy);
            });
        }
        latch.countDown();

        try {
            executorService.awaitTermination(getFastest() * 10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}