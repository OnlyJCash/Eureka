/**
 *
 */
package com.eureka.cms.rs.common;

/**
 * @author mmazzilli
 *
 */
public abstract class Constants {

	/**
	 * CTX Param Name in web.xml that contains project configuration
	 */
	public static final String CTX_PARAM_APPLICATION_RESOLVER = "EurekaApplicationClass.Resolver";


	public static final String HEADER_AUTHORIZATION_TOKEN = "authToken";

	/**
	 *
	 */
	public static final String REQUEST_ENTITY_NAME = "entity";

	/**
	 *
	 */
	public static final String PATH_VARIABLE_ENTITY_NAME = "{".concat(REQUEST_ENTITY_NAME).concat("}");
}
