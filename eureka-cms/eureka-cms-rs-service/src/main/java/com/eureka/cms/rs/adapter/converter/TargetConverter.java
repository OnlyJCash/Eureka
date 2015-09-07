/**
 *
 */
package com.eureka.cms.rs.adapter.converter;

import org.dozer.DozerConverter;

import com.eureka.cms.core.support.EurekaSupport;

/**
 * @author mmazzilli
 *
 */
@SuppressWarnings("unchecked")
public class TargetConverter extends DozerConverter<Class<?>, String> {

	private static Class<Class<?>> source;

	public TargetConverter() {
		super(source, String.class);
	}

	static {
		source = (Class<Class<?>>) Class.class.getClass();
	}

	@Override
	public String convertTo(Class<?> source, String destination) {
		return source != null ? EurekaSupport.getEurekaEntityName(source) : "";
	}

	@Override
	public Class<?> convertFrom(String source, Class<?> destination) {
		return null;
	}


}
