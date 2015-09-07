/**
 *
 */
package com.eureka.cms.core.common;

/**
 * @author Fincons
 *
 */
public enum DefaultUserRoles {

	ADMINISTRATOR("administrator", "Administrator", "*");

	private String name;
	private String label;
	private String allowedRegex;


	private DefaultUserRoles(String name, String label, String allowedRegex) {
		this.name = name;
		this.label = label;
		this.allowedRegex = allowedRegex;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * @return the allowedRegex
	 */
	public String getAllowedRegex() {
		return allowedRegex;
	}


}
