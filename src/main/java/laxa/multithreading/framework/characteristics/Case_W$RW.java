package laxa.multithreading.framework.characteristics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Chekulaev Alexey
 * Date: 11.03.12
 *
 * someone write, reader wait, came writer
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Case_W$RW {
	Fairness fairness();
	String value();
}