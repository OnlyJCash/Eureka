/**
 *
 */
package com.eureka.cms.core.service.data;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.SpringContextInitialized;
import com.eureka.cms.core.data.model.SessionToken;
import com.eureka.cms.core.data.model.EurekaUser;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public class TestAuthenticationService extends SpringContextInitialized {

	@Autowired
	private AuthenticationService authService;
	@Autowired
	private EurekaUserService userService;

	@Test
	public void testIsAuthenticated_success(){
		Optional<EurekaUser> oUser = userService.getById(1L);
		SessionToken authToken = authService.create(oUser.get());
		Assert.assertTrue(authService.isAuthenticated(authToken.getToken()));

		Optional<SessionToken> token = authService.getByToken(authToken.getToken());
		Assert.assertNotNull(token);
		Assert.assertTrue(token.isPresent());

		Optional<EurekaUser> userByToken = authService.getUserByToken(authToken.getToken());
		Assert.assertNotNull(userByToken);
		Assert.assertTrue(userByToken.isPresent());
	}
}
