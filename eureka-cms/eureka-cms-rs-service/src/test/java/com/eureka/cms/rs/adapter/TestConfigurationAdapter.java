/**
 *
 */
package com.eureka.cms.rs.adapter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.cfg.bean.EurekaConfiguration;
import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;
import com.eureka.cms.core.cfg.bean.entity.FieldDescriptor;
import com.eureka.cms.core.cfg.bean.entity.PropertyDescriptor;
import com.eureka.cms.core.service.data.AuthenticationService;
import com.eureka.cms.core.service.data.EurekaUserService;
import com.eureka.cms.rs.SpringContextInitialized;
import com.eureka.cms.rs.adapter.bean.cfg.ConfigurationBean;
import com.eureka.cms.rs.adapter.bean.cfg.entity.EntityBean;
import com.eureka.cms.rs.adapter.bean.cfg.entity.FieldBean;

/**
 * @author mmazzilli
 *
 */
public class TestConfigurationAdapter extends SpringContextInitialized {

	@Autowired
	private MockConfigurationService serviceMock;

	@Autowired
	private ConfigurationAdapter adapter;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private EurekaUserService userService;

	@Before
	public void setUp(){
		serviceMock.load();
	}

	@Test
	public void testGetConfigurationAdapted_success(){
		EurekaConfiguration cfgMocked = serviceMock.getConfiguration();

		ConfigurationBean configurationBean = adapter.getConfigurationAdapted("123456789");

		Assert.assertNotNull(configurationBean);
		Assert.assertEquals(cfgMocked.getProject().getLabel() , configurationBean.getProject().getLabel());
		Assert.assertEquals(cfgMocked.getProject().getFooter() , configurationBean.getProject().getFooterDescription());

		Assert.assertEquals(cfgMocked.getEntities().size(), configurationBean.getEntities().size());

		for (String key : cfgMocked.getEntities().keySet()) {
			EntityDescriptor entityDescriptor = cfgMocked.getEntities().get(key);
			EntityBean entityBean = configurationBean.getEntities().get(key);

			Assert.assertEquals(entityDescriptor.getLabel(), entityBean.getLabel());
			Assert.assertEquals(entityDescriptor.getName(), entityBean.getName());
			Assert.assertNotNull(entityBean.getTags());

			for (String fieldName : entityDescriptor.getFields().keySet()){
				FieldDescriptor fieldDescriptor = entityDescriptor.getFields().get(fieldName);
				FieldBean fieldBean = entityBean.getFields().get(fieldName);

				Assert.assertNotNull(fieldBean);
				Assert.assertNotNull(fieldBean.getType());
				Assert.assertEquals(fieldDescriptor.getLabel(), fieldBean.getLabel());

				if (!PropertyDescriptor.class.isAssignableFrom(fieldDescriptor.getClass())){
					Assert.assertNotNull(fieldBean.getTarget());
				}
			}
		}
	}
}
