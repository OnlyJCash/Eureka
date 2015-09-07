/**
 *
 */
package com.eureka.cms.core.cfg;

import javax.servlet.ServletContext;

/**
 * This interface provides method that will be invoked on Application Startup after
 * loading of configuration of Eureka CMS.
 *
 * @author mmazzilli
 *
 */
public interface BootApplication {

	/**
	 *
	 * @param context
	 */
	public void execute(ServletContext context);
}
