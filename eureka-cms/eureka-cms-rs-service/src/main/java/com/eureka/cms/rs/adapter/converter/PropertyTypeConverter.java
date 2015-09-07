/**
 *
 */
package com.eureka.cms.rs.adapter.converter;

import org.dozer.DozerConverter;

import com.eureka.cms.core.common.PropertyType;

/**
 * @author mmazzilli
 *
 */
public class PropertyTypeConverter extends DozerConverter<PropertyType, String> {

	public PropertyTypeConverter() {
		super(PropertyType.class, String.class);
	}

	@Override
	public String convertTo(PropertyType source, String destination) {
		return source != null ? source.name().toLowerCase() : "";
	}

	@Override
	public PropertyType convertFrom(String source, PropertyType destination) {
		return null;
	}



}
