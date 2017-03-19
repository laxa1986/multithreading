package laxa.multithreading.framework.characteristics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Chekulaev Alexey
 * Date: 11.03.12
 *
 * someone read, writer wait, came reader
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Case_R$WR {
	Fairness fairness();
	String value();
}