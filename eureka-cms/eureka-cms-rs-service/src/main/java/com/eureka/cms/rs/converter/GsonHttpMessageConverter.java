/**
 *
 */
package com.eureka.cms.rs.converter;

import com.google.gson.GsonBuilder;

/**
 *
 * @author michele.mazzilli
 *
 */
public class GsonHttpMessageConverter extends org.springframework.http.converter.json.GsonHttpMessageConverter {

	public GsonHttpMessageConverter() {
		super();
		GsonBuilder ioConverterBuilder = new GsonBuilder();
		// Exclusion Bidirectional Relation
		ioConverterBuilder.addSerializationExclusionStrategy(new BidirectionalRelationExclusionStrategy());
		setGson(ioConverterBuilder.create());
	}

}
