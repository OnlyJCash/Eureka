/**
 *
 */
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.eureka.cms.core.common.PropertyType;
import com.eureka.cms.core.config.annotation.EurekaEntity;
import com.eureka.cms.core.config.annotation.Property;
import com.eureka.cms.core.data.model.Identifier;

/**
 * @author mmazzilli
 *
 */
@Entity
@Table(name="category")
@EurekaEntity(label = "Category", plural = "")
public class Category extends Identifier{

	private static final long serialVersionUID = 4451774119462502337L;

	@Property(sortIndex = 0, type = PropertyType.TEXT)
	private String label;

	@Override
	@SequenceGenerator(name = "SEQ_CATEGORY_ID", sequenceName = "SEQ_CATEGORY_ID")
	public Long getId() {
		return super.getId();
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
