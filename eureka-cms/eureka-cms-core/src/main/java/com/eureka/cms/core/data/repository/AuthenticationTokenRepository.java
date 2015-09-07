/**
 *
 */
package com.eureka.cms.core.data.repository;

import com.eureka.cms.core.data.model.SessionToken;
import com.eureka.cms.core.data.model.EurekaUser;

/**
 * @author User
 *
 */
public interface AuthenticationTokenRepository {

	/**
	 *
	 * @param token
	 *
	 * @return
	 */
	public SessionToken findByToken(String token);

	/**
	 *
	 * @param token
	 * @return
	 */
	public EurekaUser findUserByToken(String token);

	/**
	 *
	 * @param token
	 * @param timeout to calculate new expired time
	 *
	 * @return
	 */
	public boolean updateIfIsValid(String token, Integer timeout);

	/**
	 *
	 * @param token
	 * @return
	 */
	public boolean deleteByToken(String token);

	/**
	 *
	 * @param token
	 * @param userId
	 *
	 * @return AuthenticationToken refreshed
	 */
	public SessionToken findByTokenAndUser(String token, Long userId);

}
