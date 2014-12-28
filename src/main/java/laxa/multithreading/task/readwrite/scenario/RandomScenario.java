package laxa.multithreading.task.readwrite.scenario;

import laxa.multithreading.framework.Action;
import laxa.multithreading.framework.Scenario;

/**
 * Author: Chekulaev Alexey
 * Date: 08.03.12
 */
public class RandomScenario implements Scenario {
	@Override
	public int getFastest() {
		return 0;
	}
	//	private static Random generator = new Random(System.currentTimeMillis());
//	private boolean writer;
//
//	public RandomScenario() {
//		double rand = generator.nextDouble();
//		writer = rand < Config.WRITER_FRACTION;
//	}
//
//	public void run() {
//		ThreadHelper.randomSleep(Config.JOB_START);
//		if (writer) {
//			strategy.write(this);
//		} else {
//			strategy.read();
//		}
//	}

//	public static void randomSleep(int time) {
//		if (!Config.CONST) {
//			time = random.nextInt(time);
//		}
//		sleep(time);
//	}
//
//	public static void randomSleep() {
//		randomSleep(Config.JOB_DURATION);
//	}

//	private static final int SEED = 100;
//
//	private static Random random = new Random(System.currentTimeMillis());

	@Override
	public Action[] getActions() {
		return null;
	}
}