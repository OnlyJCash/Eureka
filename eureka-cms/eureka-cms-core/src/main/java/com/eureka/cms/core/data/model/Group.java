/**
 *
 */
package com.eureka.cms.core.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.eureka.cms.core.cfg.annotation.entity.EurekaEntity;
import com.eureka.cms.core.cfg.annotation.entity.Property;
import com.eureka.cms.core.common.Constants;
import com.eureka.cms.core.common.PropertyType;

/**
 * @author User
 *
 */
@Entity
@Table(name="eureka_group")
@EurekaEntity(label = "Group", plural = "Groups", tags = Constants.TAG_EUREKA_ENTITIES_MANAGEMENT_NAME)
public class Group extends Identifier {

	private static final long serialVersionUID = 2973210984293685313L;

	@Column
	@Property(sortIndex = 0, type = PropertyType.TEXT, visibleInSearchResult=true)
	private String name;

	@Column
	@Property(sortIndex = 1, type = PropertyType.TEXT, searchable=true, visibleInSearchResult=true)
	private String label;

	@Column(length=4000)
	@Property(sortIndex = 2, type = PropertyType.TEXT, visibleInSearchResult=true)
	private String entityAllowed;

	@Override
	@SequenceGenerator(name = "SEQ_EUREKA_GROUP_ID", sequenceName = "SEQ_EUREKA_GROUP_ID")
	public Long getId() {
		return super.getId();
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
	 * @return the entityAllowed
	 */
	public String getEntityAllowed() {
		return entityAllowed;
	}

	/**
	 * @param entityAllowed the entityAllowed to set
	 */
	public void setEntityAllowed(String entityAllowed) {
		this.entityAllowed = entityAllowed;
	}

}
