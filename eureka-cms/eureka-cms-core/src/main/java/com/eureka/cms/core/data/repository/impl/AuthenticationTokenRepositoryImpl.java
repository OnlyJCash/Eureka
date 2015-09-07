/**
 *
 */
package com.eureka.cms.core.data.repository.impl;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.eureka.cms.core.data.model.SessionToken;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.repository.AuthenticationTokenRepository;
import com.eureka.cms.core.data.repository.ModelRepository;

/**
 * @author User
 *
 */
public class AuthenticationTokenRepositoryImpl extends ModelRepository<SessionToken> implements AuthenticationTokenRepository {

	public AuthenticationTokenRepositoryImpl() {
		super(SessionToken.class);
	}

	@Override
	public SessionToken findByToken(String token) {
		return (SessionToken) criteria().add(Restrictions.eq("token", token)).uniqueResult();
	}

	@Override
	public EurekaUser findUserByToken(String token) {
		return findByToken(token).getUser();
	}

	@Override
	public boolean updateIfIsValid(String token, Integer timeout) {
		Query query = currentSession().getNamedQuery(SessionToken.NQ_UPDATE_VALIDITY_TOKEN);
		query.setParameter("newExpiredAt", DateUtils.addMinutes(new Date(), timeout));
		query.setParameter("authToken", token);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean deleteByToken(String token) {
		Query query = currentSession().getNamedQuery(SessionToken.NQ_DELETE_BY_TOKEN);
		query.setParameter("authToken", token);
		return query.executeUpdate() == 1;
	}

	@Override
	public SessionToken findByTokenAndUser(String token, Long userId) {
		Query query = currentSession().getNamedQuery(SessionToken.NQ_FIND_BY_TOKEN_AND_USER);
		query.setParameter("authToken", token);
		query.setLong("userId", userId);
		return (SessionToken) query.uniqueResult();
	}

}
