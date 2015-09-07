/**
 *
 */
package com.eureka.cms.core.config;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eureka.cms.core.common.ServiceCoreNames;
import com.eureka.cms.core.service.data.EurekaUserService;

/**
 * Eureka CMS Application Initialized
 *
 * @author mmazzilli
 *
 */
public class EurekaBootApplication implements BootApplication {

	/* (non-Javadoc)
	 * @see com.eureka.cms.core.cfg.ApplicationBoot#execute(javax.servlet.ServletContext)
	 */
	@Override
	public void execute(ServletContext context) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);

		// Create Admin User
		EurekaUserService userService = webApplicationContext.getBean(ServiceCoreNames.USER_SERVICE_NAME, EurekaUserService.class);
		userService.generateAdminUserIfNotExists();

		// TODO Add other
	}

}
