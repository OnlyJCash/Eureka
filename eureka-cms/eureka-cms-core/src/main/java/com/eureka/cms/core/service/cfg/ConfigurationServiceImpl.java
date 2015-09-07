/**
 *
 */
package com.eureka.cms.core.service.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.eureka.cms.core.cfg.EurekaApplication;
import com.eureka.cms.core.cfg.bean.EurekaConfiguration;
import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;
import com.eureka.cms.core.cfg.loader.ConfigurationLoader;
import com.eureka.cms.core.support.EurekaSupport;

/**
 * This class has Singleton Scope
 *
 * @author User
 *
 */
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private ConfigurationLoader loader;

	private EurekaConfiguration configuration;

	/* (non-Javadoc)
	 * @see com.eureka.cms.core.service.cfg.ConfigurationService#getConfiguration(com.eureka.cms.core.cfg.EurekaApplication)
	 */
	@Override
	public EurekaConfiguration getConfiguration() {
		return configuration;
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
		return getConfiguration().getEntity(name);
	}


	@Override
	public void load(EurekaApplication application) {
		synchronized (EurekaConfiguration.class) {
			if (configuration == null){
				configuration = loader.load(application);
			}
		}
	}

}
