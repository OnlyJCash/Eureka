/**
 *
 */
package com.eureka.cms.rs.adapter.bean.cfg;

import java.io.Serializable;
import java.util.Map;

import org.springframework.util.Assert;

import com.eureka.cms.core.support.EurekaSupport;
import com.eureka.cms.rs.adapter.bean.cfg.entity.EntityBean;

/**
 * @author mmazzilli
 *
 */
public class ConfigurationBean implements Serializable {

	private static final long serialVersionUID = 7561055570112467501L;

	private ProjectBean project;

	private Map<String, EntityBean> entities;

	private Map<String, TagBean> tags;

	/**
	 *
	 * @param clazz
	 * @return
	 */
	public EntityBean getEntityByClass(Class<?> clazz) {
		Assert.notNull(clazz, "clazz can not be empty or null");
		return getEntity(EurekaSupport.getEurekaEntityName(clazz));
	}

	/**
	 *
	 * @param eurekaEntityName
	 *
	 * @return
	 */
	private EntityBean getEntity(String eurekaEntityName) {
		return getEntities().get(eurekaEntityName);
	}

	/**
	 *
	 * @param obj
	 * @return
	 */
	public boolean hasConfiguration(Object obj){
		return getEntityByClass(obj.getClass()) != null;
	}


	/**
	 * @return the project
	 */
	public ProjectBean getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(ProjectBean project) {
		this.project = project;
	}
	/**
	 * @return the entities
	 */
	public Map<String, EntityBean> getEntities() {
		return entities;
	}
	/**
	 * @param entities the entities to set
	 */
	public void setEntities(Map<String, EntityBean> entities) {
		this.entities = entities;
	}
	/**
	 * @return the tags
	 */
	public Map<String, TagBean> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(Map<String, TagBean> tags) {
		this.tags = tags;
	}


}
