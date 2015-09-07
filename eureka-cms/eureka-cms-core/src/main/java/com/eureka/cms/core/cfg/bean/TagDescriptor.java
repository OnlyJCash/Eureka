/**
 *
 */
package com.eureka.cms.core.cfg.bean;

/**
 * @author mmazzilli
 *
 */
public class TagDescriptor extends BasicInformation {

	private static final long serialVersionUID = -4402107314734673941L;

	public TagDescriptor() {
	}

	public TagDescriptor(String name, String label) {
		setName(name);
		setLabel(label);
	}
}
