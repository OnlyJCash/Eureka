/**
 *
 */
package com.eureka.cms.core.data.repository;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.FetchProfile;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.eureka.cms.core.data.model.Identifier;

/**
 * @author mmazzilli
 *
 */
public class EurekaDaoSupport extends HibernateDaoSupport {

	/**
	 * http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch20.html#performance-fetching
	 * Paragraph: 20.1.7. Fetch profiles
	 * To active only in CMS-Eureka Query Mode
	 *
	 */
	private String fetchProfileName;


	public boolean isSetFetchProfile(){
		return StringUtils.isNotBlank(fetchProfileName);
	}

	/**
	 *
	 * @param target
	 *
	 * @return
	 */
	public boolean isToInclude(Class<? extends Identifier> target){
		return target.isAnnotationPresent(FetchProfile.class);
	}

	/**
	 * @return the fetchProfileName
	 */
	public String getFetchProfileName() {
		return fetchProfileName;
	}

	/**
	 * @param fetchProfileName the fetchProfileName to set
	 */
	public void setFetchProfileName(String fetchProfileName) {
		this.fetchProfileName = fetchProfileName;
	}
}
