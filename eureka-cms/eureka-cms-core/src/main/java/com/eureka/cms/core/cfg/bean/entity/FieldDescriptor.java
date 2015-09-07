/**
 *
 */
package com.eureka.cms.core.cfg.bean.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.eureka.cms.core.cfg.bean.BasicInformation;
import com.eureka.cms.core.common.FieldType;
import com.eureka.cms.core.common.PropertyType;

/**
 * @author mmazzilli
 *
 */
public abstract class FieldDescriptor extends BasicInformation implements Comparable<FieldDescriptor>{

	private static final long serialVersionUID = -8603395045460482582L;

	private Integer index;

	private FieldType fieldType;
	private PropertyType type;

	private boolean localized;
	private boolean hidden;
	private boolean required;
	private boolean searchable;
	private boolean visibleInSearchResult;
	private boolean visibleInRelation;
	private String[] htmlValidations;

	@Override
	public int compareTo(FieldDescriptor field) {
		return this.index.compareTo(field.getIndex());
	}

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
	 * @return the fieldType
	 */
	public FieldType getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType the fieldType to set
	 */
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the type
	 */
	public PropertyType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PropertyType type) {
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
