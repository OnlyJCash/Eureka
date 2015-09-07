/**
 * 
 */
package com.eureka.cms.core.cfg.loader;

import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;

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
