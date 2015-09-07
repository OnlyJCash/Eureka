/**
 *
 */
package com.eureka.cms.rs.adapter.bean.cfg;

import java.io.Serializable;

/**
 * @author mmazzilli
 *
 */
public class BasicInfoBean implements Serializable {

	private static final long serialVersionUID = 4166107888548683894L;

	private String name;
	private String label;
	private String description;

	public BasicInfoBean() {
	}

	public BasicInfoBean(String name, String label) {
		setName(name);
		setLabel(label);
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
