/**
 *
 */
package com.eureka.cms.rs.adapter.bean.cfg.entity;

import java.util.List;
import java.util.Map;

import com.eureka.cms.rs.adapter.bean.cfg.BasicInfoBean;

/**
 * @author mmazzilli
 *
 */
public class EntityBean extends BasicInfoBean {

	private static final long serialVersionUID = 8402295896652978483L;

	private Map<String, FieldBean> fields;

	private List<String> tags;
	private boolean localized;

	private boolean hasMetadata;
	private boolean editInListview;

	public EntityBean() {
	}

	/**
	 * @return the fields
	 */
	public Map<String, FieldBean> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(Map<String, FieldBean> fields) {
		this.fields = fields;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
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
	 * @return the hasMetadata
	 */
	public boolean isHasMetadata() {
		return hasMetadata;
	}

	/**
	 * @param hasMetadata the hasMetadata to set
	 */
	public void setHasMetadata(boolean hasMetadata) {
		this.hasMetadata = hasMetadata;
	}

	/**
	 * @return the editInListview
	 */
	public boolean isEditInListview() {
		return editInListview;
	}

	/**
	 * @param editInListview the editInListview to set
	 */
	public void setEditInListview(boolean editInListview) {
		this.editInListview = editInListview;
	}


}
