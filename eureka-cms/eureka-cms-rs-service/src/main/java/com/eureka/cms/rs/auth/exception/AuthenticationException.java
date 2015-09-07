package com.eureka.cms.rs.auth.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author User
 *
 */
public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1369424771288897233L;

	private final HttpStatus status;
	private final String code;

	public AuthenticationException(HttpStatus status, String code, String message) {
		super(message);
		this.status = status;
		this.code = code;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getCode() {
		return this.code;
	}


}
