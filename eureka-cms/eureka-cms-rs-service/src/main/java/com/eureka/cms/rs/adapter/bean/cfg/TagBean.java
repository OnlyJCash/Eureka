/**
 *
 */
package com.eureka.cms.rs.adapter.bean.cfg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author User
 *
 */
public class TagBean extends BasicInfoBean {

	private static final long serialVersionUID = 8809734720465841161L;

	private List<BasicInfoBean> entities;

	public TagBean() {
		setEntities(new ArrayList<BasicInfoBean>());
	}

	public void addEntity(String name, String label){
		getEntities().add(new BasicInfoBean(name, label));
	}

	/**
	 * @return the entities
	 */
	public List<BasicInfoBean> getEntities() {
		return entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(List<BasicInfoBean> entities) {
		this.entities = entities;
	}
}
