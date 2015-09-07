/**
 *
 */
package com.eureka.cms.rs.auth.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.eureka.cms.rs.auth.exception.AuthenticationException;
import com.eureka.cms.rs.service.response.Response;

import cz.jirutka.spring.exhandler.handlers.RestExceptionHandler;

/**
 * @author User
 *
 */
public class AuthenticationExceptionHandler implements RestExceptionHandler<AuthenticationException, Response<String>> {

	@Override
	public ResponseEntity<Response<String>> handleException(AuthenticationException exception, HttpServletRequest request) {
		Response<String> responseError = Response.<String>fail();
		responseError.withHeaderMessage(exception.getMessage()).withHeaderCode(exception.getCode());
		return ResponseEntity.status(exception.getStatus()).body(responseError);
	}

}
