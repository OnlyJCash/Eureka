/**
 *
 */
package com.eureka.cms.rs.converter;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * This strategy excludes from Serialization all field annotated
 * with annotation that represent a relation with mappedBy attribute not null.
 * (Bidirectional Relation)
 *
 * @author mmazzilli
 *
 */
public class BidirectionalRelationExclusionStrategy implements ExclusionStrategy {

	private static final Logger logger = LoggerFactory.getLogger(BidirectionalRelationExclusionStrategy.class);

	/* (non-Javadoc)
	 * @see com.google.gson.ExclusionStrategy#shouldSkipField(com.google.gson.FieldAttributes)
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes f) {

		ManyToMany mm = f.getAnnotation(ManyToMany.class);
		if (mm != null && StringUtils.isNotBlank(mm.mappedBy())){
			logger.debug("Bidirectional Relation (M-N) excludes field: {}", f.getName());
			return true;
		}

		OneToMany om = f.getAnnotation(OneToMany.class);
		if (om != null && StringUtils.isNotBlank(om.mappedBy())){
			logger.debug("Bidirectional Relation (1-N) excludes field: {}", f.getName());
			return true;
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see com.google.gson.ExclusionStrategy#shouldSkipClass(java.lang.Class)
	 */
	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
