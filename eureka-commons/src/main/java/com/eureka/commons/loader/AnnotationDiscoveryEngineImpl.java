/**
 *
 */
package com.eureka.commons.loader;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;

import com.eureka.commons.aop.logging.Loggable;

/**
 * @author mmazzilli
 *
 */
public class AnnotationDiscoveryEngineImpl implements AnnotationDiscoveryEngine {

	@Override
	@Loggable
	public Set<Class<?>> discover(Class<? extends Annotation> annotation, String packageName) {
		Reflections reflections = new Reflections(packageName);
		return reflections.getTypesAnnotatedWith(annotation);
	}
}
