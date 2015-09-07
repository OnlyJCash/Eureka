/**
 *
 */
package com.eureka.cms.core.service.data.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.config.bean.entity.EntityDescriptor;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.EurekaModel;
import com.eureka.cms.core.data.model.Audit;
import com.eureka.cms.core.service.cfg.ConfigurationService;

/**
 * @author User
 *
 */
@Aspect
public class AspectRowMetadata {

	@Autowired
	private ConfigurationService configurationService;

	// @Before("com.eureka.cms.core.service.data.aop.ServicePointcutDefinition.withRowMetadata()")
	public void before(JoinPoint point){

		Object model = point.getArgs()[0];
		EurekaUser user = (EurekaUser) point.getArgs()[1];

		EntityDescriptor entity = configurationService.getConfiguration().getEntityByClass(model.getClass());
		if (null != entity && entity.isHasMetadata()){
			EurekaModel mwmd = (EurekaModel) model;
			if (null == mwmd.getAudit()){
				Audit rowMetadata = new Audit();
				mwmd.setAudit(rowMetadata);
				rowMetadata.setCreatedBy(user);
				rowMetadata.setClassName(entity.getClass().getCanonicalName());
			}

		}

	}
}
