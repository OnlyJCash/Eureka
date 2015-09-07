/**
 *
 */
package com.eureka.cms.rs.adapter.bean.cfg;

import java.util.List;

/**
 * @author mmazzilli
 *
 */
public class ProjectBean extends BasicInfoBean {

	private static final long serialVersionUID = 919155995953407419L;

	private String footerDescription;
	private Boolean localized;
	private List<LocaleBean> localeAllowed;
	/**
	 * @return the footerDescription
	 */
	public String getFooterDescription() {
		return footerDescription;
	}
	/**
	 * @param footerDescription the footerDescription to set
	 */
	public void setFooterDescription(String footerDescription) {
		this.footerDescription = footerDescription;
	}
	/**
	 * @return the localized
	 */
	public Boolean getLocalized() {
		return localized;
	}
	/**
	 * @param localized the localized to set
	 */
	public void setLocalized(Boolean localized) {
		this.localized = localized;
	}
	/**
	 * @return the localeAllowed
	 */
	public List<LocaleBean> getLocaleAllowed() {
		return localeAllowed;
	}
	/**
	 * @param localeAllowed the localeAllowed to set
	 */
	public void setLocaleAllowed(List<LocaleBean> localeAllowed) {
		this.localeAllowed = localeAllowed;
	}

}
