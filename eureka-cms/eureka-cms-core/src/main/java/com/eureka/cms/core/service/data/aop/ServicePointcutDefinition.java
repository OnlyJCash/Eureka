/**
 *
 */
package com.eureka.cms.core.service.data.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author User
 *
 */
@Aspect
public class ServicePointcutDefinition {

	@Pointcut("@annotation(com.eureka.cms.core.service.data.aop.RowMetadataJoinPoint)")
	//"&& @args(com.eureka.cms.core.data.model.ModelWithMetadata, com.eureka.cms.core.data.model.EurekaUser)")
	public void withRowMetadata () {
	}
}
