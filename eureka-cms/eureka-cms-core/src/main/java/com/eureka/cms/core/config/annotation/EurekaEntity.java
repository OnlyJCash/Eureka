/**
 *
 */
package com.eureka.cms.core.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author mmazzilli
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface EurekaEntity {

	String label();

	String plural();

	boolean hidden() default false;

	String description() default "";

	boolean localized() default false;

	boolean editInListview() default false;

	String[] tags() default {};

}
