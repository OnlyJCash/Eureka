/**
 *
 */
package com.example.model;

import com.eureka.cms.core.common.PropertyType;
import com.eureka.cms.core.config.annotation.EurekaEntity;
import com.eureka.cms.core.config.annotation.Property;
import com.eureka.cms.core.data.model.Identifier;

/**
 * @author mmazzilli
 *
 */
@EurekaEntity(label = "Category", plural ="categories", tags = {"category", "news"})
public class Category extends Identifier {

	private static final long serialVersionUID = -4776964777895529144L;

	@Property(sortIndex = 0, type = PropertyType.TEXT)
	private String label;

}
