/**
 *
 */
package com.eureka.cms.core.service.data;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author User
 *
 */
public abstract class AbstractCMSProperties {

	@Resource
	@Qualifier("EurekaCMSProperties")
	private Properties properties;

	/**
	 *
	 * @param name
	 * @return
	 */
	public String getAsString(String name){
		return properties.getProperty(name);
	}

	/**
	 *
	 * @param name
	 * @param defVal
	 * @return
	 */
	public Long getAsLong(String name, long defVal){
		return NumberUtils.toLong(getAsString(name), defVal);
	}

	/**
	 *
	 * @param name
	 * @param defVal
	 * @return
	 */
	public Integer getAsInteger(String name, int defVal){
		return NumberUtils.toInt(getAsString(name), defVal);
	}
}
