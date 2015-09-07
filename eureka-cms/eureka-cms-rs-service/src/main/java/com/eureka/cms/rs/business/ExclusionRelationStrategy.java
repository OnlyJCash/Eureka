/**
 *
 */
package com.eureka.cms.rs.business;

import com.eureka.cms.core.config.bean.entity.EntityDescriptor;
import com.eureka.cms.core.config.bean.entity.FieldDescriptor;
import com.eureka.cms.core.config.bean.entity.RelationDescriptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @author mmazzilli
 *
 */
public class ExclusionRelationStrategy implements ExclusionStrategy {

	private EntityDescriptor entity;

	public ExclusionRelationStrategy(EntityDescriptor entity) {
		this.entity = entity;
	}

	/* (non-Javadoc)
	 * @see com.google.gson.ExclusionStrategy#shouldSkipClass(java.lang.Class)
	 */
	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return this.entity.isRelatedWith(clazz);
	}

	/* (non-Javadoc)
	 * @see com.google.gson.ExclusionStrategy#shouldSkipField(com.google.gson.FieldAttributes)
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		if (!field.getName().equals("id")){
			FieldDescriptor fieldDescr = this.entity.getFields().get(field.getName());
			return fieldDescr != null && RelationDescriptor.class.isAssignableFrom(fieldDescr.getClass());
		}
		return false;
	}

}
