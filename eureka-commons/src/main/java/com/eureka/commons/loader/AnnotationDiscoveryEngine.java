/**
 *
 */
package com.eureka.commons.loader;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Annotation Discovery Engine interface to discover all class with an
 * annotation provided in input.
 *
 * @author mmazzilli
 *
 */
public interface AnnotationDiscoveryEngine {

	/**
	 *
	 * @param annotation
	 *            provided in input
	 * @param packageName
	 *            to discover
	 *
	 * @return Set of classes annotated with the annotation provided in input
	 */
	public Set<Class<?>> discover(Class<? extends Annotation> annotation, String packageName);
}
