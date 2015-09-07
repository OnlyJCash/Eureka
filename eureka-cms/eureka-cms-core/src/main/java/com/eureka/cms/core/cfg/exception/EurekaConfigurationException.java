/**
 * 
 */
package com.eureka.cms.core.cfg.exception;

/**
 * @author mmazzilli
 * 
 */
public class EurekaConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1929018502397139135L;

	public EurekaConfigurationException() {
		super();
	}

	public EurekaConfigurationException(String message) {
		super(message);
	}

	public EurekaConfigurationException(String message, Throwable ex) {
		super(message, ex);
	}
}
