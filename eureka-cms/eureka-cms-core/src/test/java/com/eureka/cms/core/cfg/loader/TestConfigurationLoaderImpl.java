/**
 *
 */
package com.eureka.cms.core.cfg.loader;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.SpringContextInitialized;
import com.eureka.cms.core.config.EurekaApplication;
import com.eureka.cms.core.config.EurekaApplication.ApplicationBuilder;
import com.eureka.cms.core.config.bean.EurekaConfiguration;
import com.eureka.cms.core.config.bean.entity.EntityDescriptor;
import com.eureka.cms.core.config.bean.entity.FieldDescriptor;
import com.eureka.cms.core.config.bean.entity.RelationDescriptor;
import com.eureka.cms.core.config.loader.ConfigurationLoader;
import com.example.model.Article;

/**
 * @author mmazzilli
 */
public class TestConfigurationLoaderImpl extends SpringContextInitialized {

	@Autowired
	private ConfigurationLoader cfgLoader;

	@Test
	public void testLoad_success() {

		EurekaApplication eurekaApplication =  new ApplicationBuilder().name("Test").description("Test description")
				.bindModelPackages(Article.class.getPackage().getName()).build();

		eurekaApplication.getProject().setLabel("ciao");
		EurekaConfiguration eurekaConfiguration = cfgLoader.load(eurekaApplication);

		EntityDescriptor entity = eurekaConfiguration.getEntityByClass(Article.class);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getTarget());
		Assert.assertNotNull(entity.getName());

		FieldDescriptor titleField = entity.getFields().get("title");
		Assert.assertNotNull(titleField);
		Assert.assertEquals("Title", titleField.getLabel());

		final FieldDescriptor textField = entity.getFields().get("text");
		Assert.assertNotNull(textField);
		Assert.assertEquals("Intro", textField.getLabel());


		final FieldDescriptor category = entity.getFields().get("category");
		Assert.assertNotNull(category);
		Assert.assertTrue(category.getClass().isAssignableFrom(RelationDescriptor.class));

		Integer i=1;
		for(final FieldDescriptor fd : entity.getFields().values()){
			Assert.assertEquals(i, fd.getIndex());
			i++;
		}

	}

	//	@Test(expected = IllegalArgumentException.class)
	//	public void testLoad_fail() {
	//		cfgLoader.load("");
	//	}
}
