/**
 *
 */
package com.eureka.cms.core.cfg.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eureka.cms.core.common.PropertyType;
import com.eureka.cms.core.data.model.Identifier;

/**
 * @author User
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD})
public @interface Relation {

	String label() default "";

	String description() default "";

	int sortIndex();

	PropertyType type();

	Class<? extends Identifier> target();

	boolean required() default false;

	boolean searchable() default false;

	boolean visibleInSearchResult() default false;

	boolean visibleInRelation() default false;

	String[] htmlValidations() default {};

}
