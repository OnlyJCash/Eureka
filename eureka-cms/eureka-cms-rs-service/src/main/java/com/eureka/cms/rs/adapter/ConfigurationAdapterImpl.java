/**
 *
 */
package com.eureka.cms.rs.adapter;

import java.util.Map.Entry;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.cfg.bean.EurekaConfiguration;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.service.cfg.ConfigurationService;
import com.eureka.cms.core.service.data.AuthenticationService;
import com.eureka.cms.rs.adapter.bean.cfg.ConfigurationBean;
import com.eureka.cms.rs.adapter.bean.cfg.entity.EntityBean;
import com.eureka.commons.aop.logging.Loggable;
import com.google.common.base.Optional;

/**
 * This implementation adapt configuration bean to ui bean
 *
 * @author mmazzilli
 *
 */
public class ConfigurationAdapterImpl implements ConfigurationAdapter {

	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private SecureAccessEntity secureAccessEntity;
	@Autowired
	private Mapper mapper;

	@Override
	@Loggable
	public ConfigurationBean getConfigurationAdapted(String authUserToken) {
		// Retrieve a loggedUser
		Optional<EurekaUser> loggedUser = authenticationService.getUserByToken(authUserToken);
		// Invoke AccessSecurityEntity to retrieve a configuraion relate to logged user
		EurekaConfiguration configurationByUser = secureAccessEntity.verify(configurationService.getConfiguration(), loggedUser.get());
		// UI Configuration
		ConfigurationBean configurationBean = mapper.map(configurationByUser, ConfigurationBean.class);

		for (Entry<String, EntityBean> entry : configurationBean.getEntities().entrySet()) {
			EntityBean entity = entry.getValue();
			for (String tag : entity.getTags()) {
				configurationBean.getTags().get(tag).addEntity(entity.getName(), entity.getLabel());
			}
		}

		return configurationBean;
	}

}
