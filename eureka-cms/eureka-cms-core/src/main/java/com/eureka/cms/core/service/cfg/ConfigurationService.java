/**
 *
 */
package com.eureka.cms.core.service.cfg;

import com.eureka.cms.core.config.EurekaApplication;
import com.eureka.cms.core.config.bean.EurekaConfiguration;
import com.eureka.cms.core.config.bean.entity.EntityDescriptor;

/**
 * @author mmazzilli
 *
 */
public interface ConfigurationService {

	/**
	 *
	 * @param eurekaApplication
	 *
	 * @return EurekaConfiguration
	 */
	public EurekaConfiguration getConfiguration();

	/**
	 *
	 * @param application
	 */
	public void load(EurekaApplication application);

	/**
	 *
	 * @param clazz
	 * @return
	 */
	public EntityDescriptor getEntityByClass(Class<?> clazz);

	/**
	 *
	 * @param obj
	 * @return
	 */
	public boolean hasConfiguration(Object obj);

	/**
	 *
	 * @param name
	 * @return
	 */
	public EntityDescriptor getEntity(String name);
}
