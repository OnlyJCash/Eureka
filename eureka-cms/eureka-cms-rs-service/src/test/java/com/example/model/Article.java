/**
 *
 */
package com.example.model;

import com.eureka.cms.core.cfg.annotation.entity.EurekaEntity;
import com.eureka.cms.core.cfg.annotation.entity.Property;
import com.eureka.cms.core.cfg.annotation.entity.Relation;
import com.eureka.cms.core.common.PropertyType;
import com.eureka.cms.core.data.model.Identifier;

/**
 * @author mmazzilli
 *
 */
@EurekaEntity(label = "Articolo", plural = "Articoli", tags = {"news"})
public class Article extends Identifier {

	private static final long serialVersionUID = -9031477766525836228L;

	@Property(sortIndex = 2, type=PropertyType.TEXT)
	private String title;

	@Property(sortIndex = 1, label="Intro", type=PropertyType.FORMATTED_TEXT)
	private String text;

	@Relation(sortIndex = 3, target = Category.class, type = PropertyType.MANY_TO_ONE)
	private Category category;



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


}
