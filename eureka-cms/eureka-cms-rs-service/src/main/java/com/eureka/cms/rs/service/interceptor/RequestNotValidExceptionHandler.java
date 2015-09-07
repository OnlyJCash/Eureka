/**
 *
 */
package com.eureka.cms.rs.service.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.eureka.cms.rs.service.response.Response;
import com.google.common.collect.Maps;

import cz.jirutka.spring.exhandler.handlers.RestExceptionHandler;

/**
 * This class provides a method to catch exceptions thrown by JSR-303 Validation
 * and provides a BAD_REQUEST in json format.
 *
 * @author mmazzilli
 *
 */
public class RequestNotValidExceptionHandler implements RestExceptionHandler<Exception, Response<Map<String, String>>> {

	private static final Logger logger = LoggerFactory.getLogger(RequestNotValidExceptionHandler.class);

	@Override
	public ResponseEntity<Response<Map<String, String>>> handleException(Exception exception, HttpServletRequest request) {
		logger.info("START -  {} resolves exception {}.", this.getClass().getSimpleName(), exception.getClass());
		Map<String, String> errorMsg = Maps.newHashMap();
		Response<Map<String, String>> fail = Response.fail();
		fail.getHeader().setMessages(errorMsg);

		if (ConstraintViolationException.class.isAssignableFrom(exception.getClass())){
			ConstraintViolationException cve = (ConstraintViolationException) exception;
			Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
			if (!constraintViolations.isEmpty()){
				for (ConstraintViolation<?> constraintViolation : constraintViolations) {
					fail.addHeaderMessage(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
				}
			}
		}

		// TODO To delete???
		if (MissingServletRequestParameterException.class.isAssignableFrom(exception.getClass())){
			MissingServletRequestParameterException missingParam = (MissingServletRequestParameterException) exception;
			fail.addHeaderMessage(missingParam.getParameterName(), missingParam.getMessage());
		} else if (MethodArgumentNotValidException.class.isAssignableFrom(exception.getClass())){
			MethodArgumentNotValidException methodArg = (MethodArgumentNotValidException) exception;
			for ( FieldError field : methodArg.getBindingResult().getFieldErrors()){
				fail.addHeaderMessage(field.getField(), field.getDefaultMessage());
			}
		}

		logger.info("END -  {} resolves exception {} with Bad Request.", this.getClass().getSimpleName(), exception.getClass());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fail);
	}

}
