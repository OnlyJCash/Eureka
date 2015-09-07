/**
 *
 */
package com.eureka.cms.core.service.exception;

/**
 * @author mmazzilli
 *
 */
public class DataServiceException extends RuntimeException {

	private static final long serialVersionUID = -3474485381494888844L;

	public DataServiceException(String message) {
		super(message);
	}

	public DataServiceException(String message, Throwable e) {
		super(message, e);
	}
}
