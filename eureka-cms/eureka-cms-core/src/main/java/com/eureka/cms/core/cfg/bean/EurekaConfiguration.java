/**
 *
 */
package com.eureka.cms.core.cfg.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;
import com.eureka.cms.core.cfg.exception.EurekaConfigurationException;
import com.eureka.cms.core.support.EurekaSupport;

/**
 * @author mmazzilli
 *
 */
public class EurekaConfiguration implements Serializable {

	private static final long serialVersionUID = 7557958635043617481L;

	private ProjectDescriptor project;

	private Map<String, EntityDescriptor> entities;
	private Map<String, TagDescriptor> tags;

	public EurekaConfiguration() {
		setEntities(new LinkedHashMap<String, EntityDescriptor>());
		setTags(new LinkedHashMap<String, TagDescriptor>());
	}

	/**
	 *
	 * @param entity
	 *
	 * @throws EurekaConfigurationException
	 */
	public void addEntity(EntityDescriptor entity) throws EurekaConfigurationException {
		if (this.entities.containsKey(entity.getName())) {
			throw new EurekaConfigurationException("ERROR - Entity with name " + entity.getName() + " already exist in Configuration");
		}
		this.entities.put(entity.getName(), entity);
	}

	/**
	 *
	 * @param clazz
	 * @return
	 */
	public EntityDescriptor getEntityByClass(Class<?> clazz) {
		Assert.notNull(clazz, "clazz can not be empty or null");
		return getEntity(EurekaSupport.getEurekaEntityName(clazz));
	}

	public boolean hasConfiguration(Object obj){
		return getEntityByClass(obj.getClass()) != null;
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	public EntityDescriptor getEntity(String name) {
		return this.entities.get(name);
	}

	/**
	 * @return the project
	 */
	public ProjectDescriptor getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(ProjectDescriptor project) {
		this.project = project;
	}

	/**
	 * @return the entities
	 */
	public Map<String, EntityDescriptor> getEntities() {
		return entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(Map<String, EntityDescriptor> entities) {
		this.entities = entities;
	}

	public Map<String, TagDescriptor> getTags() {
		return tags;
	}

	public void setTags(Map<String, TagDescriptor> tags) {
		this.tags = tags;
	}
}
