/**
 *
 */
package com.eureka.cms.rs.context;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eureka.cms.core.common.ServiceCoreNames;
import com.eureka.cms.core.config.Application;
import com.eureka.cms.core.config.BootApplication;
import com.eureka.cms.core.config.EurekaApplication;
import com.eureka.cms.core.service.cfg.ConfigurationService;
import com.eureka.cms.rs.common.Constants;

/**
 * @author User
 *
 */
public class EurekaContextLoaderListener extends ContextLoaderListener {

	private static final Logger logger = LoggerFactory.getLogger(EurekaContextLoaderListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);

		ServletContext servletContext =  event.getServletContext();
		Class<?> eurekaApplication = null;
		try {
			String eurekaApplicationClass = servletContext.getInitParameter(Constants.CTX_PARAM_APPLICATION_RESOLVER);
			logger.info("START - {} reading: {} {}", new Object[]{this.getClass().getSimpleName(), Constants.CTX_PARAM_APPLICATION_RESOLVER, eurekaApplicationClass});
			eurekaApplication = Class.forName(eurekaApplicationClass);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			closeWebApplicationContext(servletContext);
			contextDestroyed(event);
			return;
		}

		if (Application.class.isAssignableFrom(eurekaApplication)){

			Application appInterface = null;
			try {
				appInterface = (Application) eurekaApplication.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error(e.getMessage(), e);
				closeWebApplicationContext(servletContext);
				contextDestroyed(event);
				return;
			}

			// Call configure in order to get EurekaApplication Configuration
			EurekaApplication eurekaAppInst = appInterface.configure();

			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

			// Invoke Configuration Service to load configuration.
			ConfigurationService cs = webApplicationContext.getBean(ServiceCoreNames.CONFIGURATION_SERVICE_NAME, ConfigurationService.class);
			cs.load(eurekaAppInst);

			List<BootApplication> applicationBoots = eurekaAppInst.getBootApplications();
			if (null != applicationBoots && applicationBoots.size() > 0){
				logger.debug("Set of BootApplications is not empty. Size {}", applicationBoots.size());
				for (BootApplication applicationBoot : applicationBoots) {
					logger.info("ApplicationBoot - Invoke {} execute", applicationBoot.getClass().getSimpleName());
					applicationBoot.execute(servletContext);
				}
			}

		}
	}
}