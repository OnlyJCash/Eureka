/**
 *
 */
package com.eureka.cms.rs.adapter;

import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.cfg.EurekaApplication;
import com.eureka.cms.core.cfg.EurekaApplication.ApplicationBuilder;
import com.eureka.cms.core.cfg.bean.EurekaConfiguration;
import com.eureka.cms.core.service.cfg.ConfigurationService;
import com.example.model.Article;

/**
 * @author User
 *
 */
public class MockConfigurationService  {

	@Autowired
	private ConfigurationService cs;

	public MockConfigurationService() {
	}

	public EurekaConfiguration getConfiguration() {
		return cs.getConfiguration();
	}

	public void load(){
		System.out.println("Load configuration ...................");
		ApplicationBuilder applicationBuilder = new ApplicationBuilder().name("Test").description("Test");

		applicationBuilder.bindEurekaEntities().bindEurekaBootApplication();
		applicationBuilder.bindModelPackages(Article.class.getPackage().getName());
		applicationBuilder.withTag("news", "News").withTag("category", "Category");

		EurekaApplication application = applicationBuilder.build();

		cs.load(application);
		System.out.println("Loading configuration is over "+ applicationBuilder);
	}
}
