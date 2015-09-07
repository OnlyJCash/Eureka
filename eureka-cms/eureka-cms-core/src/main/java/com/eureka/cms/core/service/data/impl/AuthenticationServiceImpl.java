/**
 *
 */
package com.eureka.cms.core.service.data.impl;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.Assert;

import com.eureka.cms.core.common.Properties;
import com.eureka.cms.core.data.model.SessionToken;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.repository.AuthenticationTokenRepository;
import com.eureka.cms.core.service.data.AbstractModelService;
import com.eureka.cms.core.service.data.AuthenticationService;
import com.eureka.commons.aop.logging.Loggable;
import com.eureka.commons.security.SecurityToken;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public class AuthenticationServiceImpl extends AbstractModelService<SessionToken> implements AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	public AuthenticationServiceImpl(PagingAndSortingRepository<SessionToken, Serializable> repository) {
		super(repository);
	}

	@Override
	@Loggable
	public Optional<SessionToken> getByToken(String authToken) {
		Assert.hasText(authToken, "Authentication Token must be set!");
		return Optional.fromNullable(((AuthenticationTokenRepository) repository).findByToken(authToken));
	}

	@Override
	@Loggable
	public Optional<EurekaUser> getUserByToken(String authToken) {
		Assert.hasText(authToken, "Authentication Token must be set!");
		Optional<SessionToken> oAuthToken = getByToken(authToken);
		if (oAuthToken.isPresent()){
			return Optional.of(oAuthToken.get().getUser());
		}
		return Optional.absent();
	}

	@Override
	@Loggable
	public SessionToken create(EurekaUser user) {
		Assert.notNull(user, "To create Auth Token user parameter must be set!");
		SessionToken authToken = new SessionToken();
		authToken.setUser(user);
		authToken.setCreatedAt(new Date());
		authToken.setExpiredAt(DateUtils.addMinutes(authToken.getCreatedAt(), getAsInteger(Properties.TOKEN_EXPIRED_TIMEOUT, 15)));
		authToken.setToken(SecurityToken.unique(user.getId(), user.getUsername()));
		repository.save(authToken);
		return authToken;
	}

	@Override
	@Loggable
	public boolean isAuthenticated(String token) {
		Assert.hasText(token, "Token must be set!");
		Integer timeout = getAsInteger(Properties.TOKEN_EXPIRED_TIMEOUT, 15);
		return ((AuthenticationTokenRepository) repository).updateIfIsValid(token, timeout);
	}

	@Override
	@Loggable
	public boolean invalidateToken(String token) {
		Assert.hasText(token, "Token must be set!");
		return ((AuthenticationTokenRepository) repository).deleteByToken(token);
	}

	@Override
	@Loggable
	public Optional<SessionToken> refresh(String token, Long userId) {
		Assert.hasText(token, "Token must be set!");
		Assert.isTrue(userId != null && userId > 0, "userId must be set!");
		SessionToken authToken = null;
		try {
			authToken = ((AuthenticationTokenRepository) repository).findByTokenAndUser(token, userId);
		} catch (DataAccessException ex){
			logger.error(ex.getMessage(), ex);
			return Optional.absent();
		}

		if (null != authToken){
			EurekaUser user = authToken.getUser();
			authToken.setExpiredAt(DateUtils.addMinutes(new Date(), getAsInteger(Properties.TOKEN_EXPIRED_TIMEOUT, 15)));
			authToken.setToken(SecurityToken.unique(user.getId(), user.getUsername()));
			repository.save(authToken);
			return Optional.fromNullable(authToken);
		}
		return Optional.absent();
	}

}
