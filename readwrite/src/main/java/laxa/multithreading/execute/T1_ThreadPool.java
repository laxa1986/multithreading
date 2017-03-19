package laxa.multithreading.execute;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: Chekulaev Alexey
 * Date: 25.02.12
 */
public class T1_ThreadPool implements ExecTask {
	public void doIt() {
		Callable<Integer> callable = () -> 1;
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Future<Integer> future = executor.submit(callable);
		try {
			System.out.println("Future value: " + future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}