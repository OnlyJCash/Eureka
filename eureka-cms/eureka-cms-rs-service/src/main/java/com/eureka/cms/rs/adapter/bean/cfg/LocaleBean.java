/**
 *
 */
package com.eureka.cms.rs.adapter.bean.cfg;

import java.io.Serializable;

/**
 * @author mmazzilli
 *
 */
public class LocaleBean implements Serializable {

	private static final long serialVersionUID = 4269710727017639219L;

	private String language;
	private String country;
	private String label;

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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

}
