/**
 *
 */
package com.eureka.cms.core.cfg.loader;

import com.eureka.cms.core.cfg.EurekaApplication;
import com.eureka.cms.core.cfg.bean.EurekaConfiguration;

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
