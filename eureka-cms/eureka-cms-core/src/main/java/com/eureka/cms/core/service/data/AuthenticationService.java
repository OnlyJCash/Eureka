/**
 *
 */
package com.eureka.cms.core.service.data;

import com.eureka.cms.core.data.model.SessionToken;
import com.eureka.cms.core.data.model.EurekaUser;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public interface AuthenticationService {

	/**
	 *
	 * @param authToken
	 * @return
	 */
	public Optional<SessionToken> getByToken(String authToken);

	/**
	 *
	 * @param authToken
	 *
	 * @return
	 */
	public Optional<EurekaUser> getUserByToken(String authToken);

	/**
	 *
	 * @param user
	 *
	 * @return AuthenticationToken
	 */
	public SessionToken create(EurekaUser user);

	/**
	 *
	 * @param token
	 * @return
	 */
	public boolean isAuthenticated(String token);

	/**
	 *
	 * @param token
	 * @return
	 */
	public boolean invalidateToken(String token);

	/**
	 *
	 * @param token
	 * @param userId
	 * @return
	 */
	public Optional<SessionToken> refresh(String token, Long userId);
}
