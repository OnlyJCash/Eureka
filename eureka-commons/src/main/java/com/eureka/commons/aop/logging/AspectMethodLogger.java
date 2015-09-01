/**
 *
 */
package com.eureka.commons.aop.logging;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eureka.commons.aop.logging.Loggable.Level;

/**
 * @author User
 *
 */
@Aspect
public class AspectMethodLogger {

	@Around("execution(* *(..)) && @annotation(com.eureka.commons.aop.logging.Loggable)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long start = System.currentTimeMillis();

		Class<?> target = point.getTarget().getClass();
		Logger logger = LoggerFactory.getLogger(target);

		Method method = ((MethodSignature) point.getSignature()).getMethod();
		Level level = null;

		if (method.getDeclaringClass().isInterface()){
			method = point.getTarget().getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
		}

		level = method.getAnnotation(Loggable.class).level();

		if (Level.INFO.equals(level)){
			logger.info("START - {}.{}", point.getTarget().getClass().getSimpleName(), method.getName());
		} else {
			logger.debug("START - {}.{} with params {}", new Object[]{point.getTarget().getClass().getSimpleName(), method.getName(), point.getArgs()});
		}

		Object result = point.proceed();

		if (Level.INFO.equals(level)){
			logger.info("END - {}.{}", point.getTarget().getClass().getSimpleName(), method.getName());
		} else {
			logger.debug("END - {}.{} with executionTime {}",point.getTarget().getClass().getSimpleName(), method.getName(), (System.currentTimeMillis() - start));
		}
		return result;
	}
}
