/**
 *
 */
package com.eureka.cms.rs.adapter;

import com.eureka.cms.core.config.bean.EurekaConfiguration;
import com.eureka.cms.core.data.model.EurekaUser;

/**
 * @author Fincons
 *
 */
public interface SecureAccessEntity {

	/**
	 *
	 * @param source Standard CMS Configuration
	 * @param user loggedUser
	 *
	 * @return EurekaConfiguration related to user provided in input
	 */
	public EurekaConfiguration verify(EurekaConfiguration source, EurekaUser user);

	/**
	 *
	 * @param loggedUser
	 * @param entiyName
	 *
	 * @return
	 */
	public boolean canAccess(EurekaUser loggedUser, String entiyName);

}
