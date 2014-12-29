package laxa.multithreading.framework;

import java.util.Date;

public final class TestRunner {
	public TestRunner() {
	}

	public <S extends Strategy> void test(Scenario<S> scenario, S strategy) {
		System.out.println("*** " + scenario.getClass().getSimpleName() + (scenario instanceof FixedScenario ? " *** " + ((FixedScenario)scenario).getFastest() : ""));

		Date start = new Date();

		scenario.run(strategy);

		Date end = new Date();
		Date duration = new Date(end.getTime() - start.getTime());
		System.out.println(strategy.getClass().getSimpleName() + " duration: " + duration.getTime() + "ms (" + duration.getSeconds() + "s " + duration.getTime() % 1000 + "ms)");
	}

	public <S extends Strategy> void test(Scenario<S>[] scenarios, S strategy) {
		for (Scenario<S> scenario : scenarios) {
			test(scenario, strategy);
		}
	}

	public <S extends Strategy> void test(Scenario<S> scenario, S[] strategies) {
		for (S strategy : strategies) {
			test(scenario, strategy);
		}
	}
}