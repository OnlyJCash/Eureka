package com.eureka.cms.rs.service.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.service.data.AuthenticationService;
import com.eureka.cms.rs.adapter.SecureAccessEntity;
import com.eureka.cms.rs.common.Constants;
import com.eureka.commons.aop.logging.Loggable;
import com.google.common.base.Optional;

/**
 *
 * @author Fincons
 *
 */
public class SecureAccessEntityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecureAccessEntityInterceptor.class);

	@Autowired
	private SecureAccessEntity secureAccessEntity;
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	@Loggable
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Map<String,Object> pathVariables = (Map<String,Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		String entityName = (String) pathVariables.get(Constants.REQUEST_ENTITY_NAME);
		logger.debug("Read entity name: {}", entityName);

		String authToken = request.getHeader(Constants.HEADER_AUTHORIZATION_TOKEN);
		Optional<EurekaUser> loggedUser = authenticationService.getUserByToken(authToken);
		boolean canAccess = secureAccessEntity.canAccess(loggedUser.get(), entityName);
		if (!canAccess){
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return canAccess;
		}

		return super.preHandle(request, response, handler);
	}
}
