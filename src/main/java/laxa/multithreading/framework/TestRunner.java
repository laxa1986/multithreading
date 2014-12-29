package laxa.multithreading.framework;

import laxa.multithreading.task.readwrite.strategy.Strategy;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public final class TestRunner {
	private Strategy strategy;
	
	public TestRunner(Strategy strategy) {
		this.strategy = strategy;
//		System.out.println("*** " + scenario.getClass().getSimpleName() + " *** " + scenario.getFastest());
	}

	private CountDownLatch latch;

	public void test(Scenario scenario) {
		Action[] actions = scenario.getActions();

		latch = new CountDownLatch(actions.length);
		Thread[] threads = new Thread[actions.length];
		for (int i=0; i<actions.length; i++) {
			final Action action = actions[i];
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						latch.await();
					} catch (InterruptedException e) {
						return;
					}
					new StrategyExecutor(strategy, action).run();
				}
			};
			Thread thread = new Thread(runnable);
			threads[i] = thread;
		}

		Date start = new Date();
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		Date end = new Date();
		Date duration = new Date(end.getTime() - start.getTime());
		System.out.println(strategy.getName() + " duration: " + duration.getTime() + "ms (" + duration.getSeconds() + "s " + duration.getTime()%1000 + "ms)");
	}

	public void test(Scenario[] scenarios) {
		for (Scenario scenario : scenarios) {
			test(scenario);
		}
	}
}