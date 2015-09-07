/**
 *
 */
package com.eureka.cms.core.support;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author mmazzilli
 *
 */
public abstract class EurekaSupport {

	/**
	 *
	 * @param target
	 *
	 * @return
	 */
	public static String getEurekaEntityName(Class<?> target) {
		return target.getSimpleName().toLowerCase();
	}

	/**
	 *
	 * @param args
	 *
	 * @return
	 */
	public static Set<String> asSet(String... args) {
		if (args != null && args.length > 0) {
			return Sets.newHashSet(args);
		}
		return null;
	}
}
