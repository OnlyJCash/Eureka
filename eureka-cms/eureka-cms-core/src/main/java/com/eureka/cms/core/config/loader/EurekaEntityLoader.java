/**
 * 
 */
package com.eureka.cms.core.config.loader;

import com.eureka.cms.core.config.bean.entity.EntityDescriptor;

/**
 * @author mmazzilli
 * 
 */
public interface EurekaEntityLoader {

	/**
	 * 
	 * @param target
	 * 
	 * @return
	 */
	public EntityDescriptor load(Class<?> target);
}
