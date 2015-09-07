/**
 *
 */
package com.eureka.cms.core.service.data;

import com.eureka.cms.core.data.model.EurekaUser;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public interface EurekaUserService extends ModelService<EurekaUser> {

	public Optional<EurekaUser> getUserByCredential(String username, String password);

	public Optional<EurekaUser> generateAdminUserIfNotExists();
}
