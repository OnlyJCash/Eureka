/**
 *
 */
package com.eureka.cms.rs.adapter.bean.cfg.entity;

import com.eureka.cms.rs.adapter.bean.cfg.BasicInfoBean;

/**
 * @author mmazzilli
 *
 */
public class FieldBean extends BasicInfoBean {

	private static final long serialVersionUID = 1898376256516348651L;

	private Integer index;
	private String type;
	private boolean localized;
	private boolean hidden;
	private boolean required;
	private boolean searchable;
	private boolean visibleInSearchResult;
	private boolean visibleInRelation;
	private String[] htmlValidations;


	private String target;

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the localized
	 */
	public boolean isLocalized() {
		return localized;
	}

	/**
	 * @param localized the localized to set
	 */
	public void setLocalized(boolean localized) {
		this.localized = localized;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the searchable
	 */
	public boolean isSearchable() {
		return searchable;
	}

	/**
	 * @param searchable the searchable to set
	 */
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	/**
	 * @return the visibleInSearchResult
	 */
	public boolean isVisibleInSearchResult() {
		return visibleInSearchResult;
	}

	/**
	 * @param visibleInSearchResult the visibleInSearchResult to set
	 */
	public void setVisibleInSearchResult(boolean visibleInSearchResult) {
		this.visibleInSearchResult = visibleInSearchResult;
	}

	/**
	 * @return the visibleInRelation
	 */
	public boolean isVisibleInRelation() {
		return visibleInRelation;
	}

	/**
	 * @param visibleInRelation the visibleInRelation to set
	 */
	public void setVisibleInRelation(boolean visibleInRelation) {
		this.visibleInRelation = visibleInRelation;
	}

	/**
	 * @return the htmlValidations
	 */
	public String[] getHtmlValidations() {
		return htmlValidations;
	}

	/**
	 * @param htmlValidations the htmlValidations to set
	 */
	public void setHtmlValidations(String[] htmlValidations) {
		this.htmlValidations = htmlValidations;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}


}
