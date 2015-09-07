/**
 *
 */
package com.eureka.cms.rs.adapter;

import com.eureka.cms.rs.adapter.bean.cfg.ConfigurationBean;

/**
 * @author mmazzilli
 *
 */
public interface ConfigurationAdapter {

	/**
	 *
	 * @param packages
	 * @return ConfigurationBean
	 */
	public ConfigurationBean getConfigurationAdapted(String authUserToken);
}
