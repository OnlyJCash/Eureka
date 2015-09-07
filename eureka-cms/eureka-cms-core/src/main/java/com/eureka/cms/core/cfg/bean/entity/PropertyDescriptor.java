/**
 *
 */
package com.eureka.cms.core.cfg.bean.entity;

import com.eureka.cms.core.common.FieldType;

/**
 * @author mmazzilli
 *
 */
public class PropertyDescriptor extends FieldDescriptor {

	private static final long serialVersionUID = 1775371032584909040L;

	public PropertyDescriptor() {
		setFieldType(FieldType.PROPERTY);
	}

}
