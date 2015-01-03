package laxa.multithreading.framework;

import javax.annotation.Nonnull;
import java.util.Date;

public final class TestRunner {
	public TestRunner() {
	}

	private static void CR() {
		System.out.println();
	}

	public <S extends Strategy> Date test(@Nonnull Scenario<S> scenario, S strategy) {
		Date start = new Date();

		scenario.run(strategy);

		Date end = new Date();
		return new Date(end.getTime() - start.getTime());
	}

	public <S extends Strategy> void testStrategy(Scenario<S>[] scenarios, S strategy) {
		System.out.println("Test strategy " + formatStrategy(strategy));

		for (Scenario<S> scenario : scenarios) {
			Date duration = test(scenario, strategy);
			System.out.println(scenario.getClass().getSimpleName() + " duration: " + formatDuration(duration));
		}
		CR();
	}

	public <S extends Strategy> void testScenario(Scenario<S> scenario, S[] strategies) {
		System.out.println("Test scenario " + formatScenario(scenario));

		for (S strategy : strategies) {
			Date duration = test(scenario, strategy);
			System.out.println(formatStrategy(strategy) + " duration: " + formatDuration(duration));
		}
		CR();
	}

	private static String formatDuration(Date duration) {
		return duration.getTime() + "ms (" + duration.getSeconds() + "s " + duration.getTime() % 1000 + "ms)";
	}

	private static String formatScenario(Scenario scenario) {
		return scenario.getClass().getSimpleName() + scenario.getClass().getSimpleName() + (scenario instanceof FixedScenario ? " *** " + ((FixedScenario)scenario).getFastest() : "");
	}

	private static String formatStrategy(Strategy strategy) {
		return strategy.getClass().getSimpleName();
	}
}