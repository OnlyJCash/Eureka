/**
 *
 */
package com.eureka.cms.core.cfg.bean;

import java.util.Locale;
import java.util.Set;

/**
 * @author mmazzilli
 *
 */
public class ProjectDescriptor extends BasicInformation {

	private static final long serialVersionUID = 5311289061291752238L;

	private String footer;
	private Set<Locale> localeAllowed;
	private Locale defaultLocale;
	/**
	 * @return the footer
	 */
	public String getFooter() {
		return footer;
	}
	/**
	 * @param footer the footer to set
	 */
	public void setFooter(String footer) {
		this.footer = footer;
	}
	/**
	 * @return the localeAllowed
	 */
	public Set<Locale> getLocaleAllowed() {
		return localeAllowed;
	}
	/**
	 * @param localeAllowed the localeAllowed to set
	 */
	public void setLocaleAllowed(Set<Locale> localeAllowed) {
		this.localeAllowed = localeAllowed;
	}
	/**
	 * @return the defaultLocale
	 */
	public Locale getDefaultLocale() {
		return defaultLocale;
	}
	/**
	 * @param defaultLocale the defaultLocale to set
	 */
	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

}
