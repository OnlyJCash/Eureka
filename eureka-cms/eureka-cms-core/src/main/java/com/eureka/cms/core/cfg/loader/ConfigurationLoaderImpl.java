/**
 *
 */
package com.eureka.cms.core.cfg.loader;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.eureka.cms.core.cfg.EurekaApplication;
import com.eureka.cms.core.cfg.annotation.entity.EurekaEntity;
import com.eureka.cms.core.cfg.bean.EurekaConfiguration;
import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;
import com.eureka.commons.aop.logging.Loggable;
import com.eureka.commons.aop.logging.Loggable.Level;
import com.eureka.commons.loader.AnnotationDiscoveryEngine;

/**
 * @author mmazzilli
 *
 */
public class ConfigurationLoaderImpl implements ConfigurationLoader {

	@Autowired
	private AnnotationDiscoveryEngine annotationDiscovery;
	@Autowired
	private EurekaEntityLoader entityLoader;


	@Override
	@Loggable(level=Level.INFO)
	public EurekaConfiguration load(EurekaApplication eurekaApplication) {
		Assert.notEmpty(eurekaApplication.getModelPackages(), "Model packages to scan must be set!");

		EurekaConfiguration eurekaConfiguration = new EurekaConfiguration();

		Assert.notNull(eurekaApplication.getProject(), "Project descriptor must be set!");
		eurekaConfiguration.setProject(eurekaApplication.getProject());

		//eurekaConfiguration.getProject().setDefaultLocale(eurekaApplication.getDefaultLocale());
		// eurekaConfiguration.getProject().setLocaleAllowed(Sets.newHashSet(eurekaApplication.getLocaleAllowed()));

		eurekaConfiguration.setTags(eurekaApplication.getTags());


		for (String pn : eurekaApplication.getModelPackages()) {
			Set<Class<?>> classes = annotationDiscovery.discover(EurekaEntity.class, pn);
			if (CollectionUtils.isNotEmpty(classes)) {
				for (Class<?> target : classes) {
					EntityDescriptor entity = entityLoader.load(target);
					eurekaConfiguration.addEntity(entity);
				}
			}
		}
		return eurekaConfiguration;
	}
}
