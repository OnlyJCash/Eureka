/**
 *
 */
package com.eureka.cms.core.data.exception;

import org.springframework.dao.DataAccessException;

/**
 * This class represent an Exception thrown by Data Repository
 *
 * @author mmazzilli
 *
 */
public class DataRepositoryException extends DataAccessException {

	private static final long serialVersionUID = -8330571662407862288L;

	public DataRepositoryException(String message) {
		super(message);
	}

	public DataRepositoryException(String message, Throwable ex) {
		super(message, ex);
	}
}
