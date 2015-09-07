/**
 * 
 */
package com.eureka.cms.core.common;

/**
 * @author mmazzilli
 *
 */
public enum PropertyType {

	//@formatter:off
	TEXT, FORMATTED_TEXT, EMAIL,

	DATE, TIMESTAMP,

	INTEGER, LONG, NUMERIC,

	SWITCH, 

	ATTACHMENT, ATTACHMENTS,

	MANY_TO_ONE, MANY_TO_MANY;
	//@formatter:on
}
