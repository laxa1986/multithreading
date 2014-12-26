package laxa.multithreading.readwrite.framework;

import laxa.multithreading.readwrite.action.Action;
import laxa.multithreading.readwrite.scenario.*;
import laxa.multithreading.readwrite.strategy.*;

import java.util.Date;

public final class TestRunner {
	private static Scenario scenario;
	
	private static void setScenario(Scenario scenario) {
		TestRunner.scenario = scenario;
		System.out.println("*** " + scenario.getClass().getSimpleName() + " *** " + scenario.getFastest());
	}

	@SuppressWarnings("deprecation")
	private static void testImpl(Strategy strategy) throws Exception {
		Action[] actions = scenario.getActions();
		
		Thread[] threads = new Thread[actions.length];
		for (int i=0; i<actions.length; i++) {
			Action action = actions[i];
			Runnable runnable = new StrategyExecutor(strategy, action);
			Thread thread = new Thread(runnable);
			threads[i] = thread;
		}

		Date start = new Date();
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
		Date end = new Date();
		Date duration = new Date(end.getTime() - start.getTime());
		System.out.println(strategy.getName() + " duration: " + duration.getTime() + "ms (" + duration.getSeconds() + "s " + duration.getTime()%1000 + "ms)");
	}
	
	private static void CR() {
		System.out.println();
	}

	private static void fullTest(Scenario scenario) throws Exception {
		testImpl(new T01_Synchronized());
		testImpl(new T05_ReentrantLock()); CR();

		testImpl(new T02_StrategyRead());
		testImpl(new T06_RWLock()); CR();

		testImpl(new T03_StrategyWrite()); CR();

		testImpl(new T04_StrategyFair());
		testImpl(new T07_RWLockFair()); CR();
	}

	private static void testScenario(Scenario scenario) throws Exception {
		setScenario(scenario); CR();

//		fullTest(scenario);

		testImpl(new T02_StrategyRead());
		testImpl(new T08_CustomLock()); CR();
	}

	public static void main(String[] args) throws Exception {
		testScenario(new C02_RR());
		testScenario(new C03_RWR());
		testScenario(new C04_W$RW_R());
		testScenario(new C05_W$WR_R());
		testScenario(new C06_R$WR_R());
		testScenario(new C07_R$WRW_R());
//		testScenario(new SimpleActionsScenario());
	}
}