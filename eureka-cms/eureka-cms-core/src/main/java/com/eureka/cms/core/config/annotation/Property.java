/**
 *
 */
package com.eureka.cms.core.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eureka.cms.core.common.PropertyType;

/**
 * @author mmazzilli
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD})
public @interface Property {

	String label() default "";

	String description() default "";

	int sortIndex();

	PropertyType type();

	boolean hidden() default false;

	boolean localized() default false;

	boolean required() default false;

	boolean searchable() default false;

	boolean visibleInSearchResult() default false;

	boolean visibleInRelation() default false;

	String[] htmlValidations() default {};

	// TODO Validate (interface java class)

}
