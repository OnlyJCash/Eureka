/**
 *
 */
package com.eureka.cms.core.config.bean.entity;

import com.eureka.cms.core.common.FieldType;
import com.eureka.cms.core.data.model.Identifier;

/**
 * @author mmazzilli
 *
 */
public class RelationDescriptor extends FieldDescriptor {

	private static final long serialVersionUID = 394218051865932000L;

	private Class<? extends Identifier> target;

	public RelationDescriptor() {
		setFieldType(FieldType.RELATION);
	}

	/**
	 * @return the target
	 */
	public Class<? extends Identifier> getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Class<? extends Identifier> target) {
		this.target = target;
	}

}
