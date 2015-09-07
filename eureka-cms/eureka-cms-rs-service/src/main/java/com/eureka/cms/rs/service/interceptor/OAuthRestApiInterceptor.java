package com.eureka.cms.rs.service.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eureka.cms.core.service.data.AuthenticationService;
import com.eureka.cms.rs.auth.annotation.Public;
import com.eureka.cms.rs.auth.exception.AuthenticationException;
import com.eureka.cms.rs.common.Constants;
import com.eureka.commons.aop.logging.Loggable;
import com.eureka.commons.aop.logging.Loggable.Level;

/**
 *
 * @author mmazzilli
 *
 */
public class OAuthRestApiInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(OAuthRestApiInterceptor.class);

	@Autowired
	private AuthenticationService authService;

	@Override
	@Loggable(level=Level.INFO)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Method method = getResourceMethod(handler);

		if (isAnnotationPresent(method, Public.class)){
			logger.debug("Public ResourceMethod {} - No authentication required!", handler);
			return true;
		}

		logger.debug("Method {} required authentication to access API Rest Resource Method...", method.getName());

		String authToken = request.getHeader(Constants.HEADER_AUTHORIZATION_TOKEN);

		if (StringUtils.isBlank(authToken)){
			throw new AuthenticationException(HttpStatus.UNAUTHORIZED, "EMPTY_TOKEN", "Authentication Token is not present in Http Header");
		}

		boolean isAuthenticated = authService.isAuthenticated(authToken);

		// TODO Remove
		// isAuthenticated = true;
		if (!isAuthenticated){
			throw new AuthenticationException(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN_FOR_RS_API", "Invalid token provided in input");
		}

		return isAuthenticated;
	}

	/**
	 * @param method
	 * @param annotation TODO
	 * @return
	 */
	protected boolean isAnnotationPresent(Method method, Class<? extends Annotation> annotation) {
		return method.isAnnotationPresent(annotation);
	}

	/**
	 * @param handler
	 * @return
	 */
	protected Method getResourceMethod(Object handler) {
		HandlerMethod resourceMethod = (HandlerMethod) handler;
		return resourceMethod.getMethod();
	}
}
