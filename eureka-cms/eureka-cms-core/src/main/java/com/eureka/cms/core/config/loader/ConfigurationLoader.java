/**
 *
 */
package com.eureka.cms.core.config.loader;

import com.eureka.cms.core.config.EurekaApplication;
import com.eureka.cms.core.config.bean.EurekaConfiguration;

/**
 * @author mmazzilli
 *
 */
public interface ConfigurationLoader {

	/**
	 *
	 * @param eurekaApplication
	 *
	 * @return Eureka Configuration
	 */
	public EurekaConfiguration load(EurekaApplication eurekaApplication);

}
