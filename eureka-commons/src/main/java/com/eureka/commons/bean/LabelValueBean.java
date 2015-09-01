/**
 *
 */
package com.eureka.commons.bean;

import java.io.Serializable;

/**
 * @author michele.mazzilli
 *
 */
public class LabelValueBean implements Serializable {

	private static final long serialVersionUID = -1989718235917012895L;

	private String label;
	private String value;


	public LabelValueBean(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public LabelValueBean(String label, Long value) {
		this.label = label;
		this.value = String.valueOf(value);
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
