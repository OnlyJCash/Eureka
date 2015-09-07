/**
 *
 */
package com.eureka.cms.core;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eureka.cms.core.cfg.EurekaApplication;
import com.eureka.cms.core.cfg.EurekaApplication.ApplicationBuilder;
import com.eureka.cms.core.service.cfg.ConfigurationService;
import com.example.model.Article;




/**
 * @author mmazzilli
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-eureka-application-context.xml")
public abstract class SpringContextInitialized {

	@Autowired
	protected ConfigurationService cfgService;

	@Before
	public  void setUp(){
		ApplicationBuilder appBuilder = new ApplicationBuilder();
		appBuilder.name("Test").description("Test description")
		.bindEurekaEntities()
		.bindModelPackages(Article.class.getPackage().getName())
		.withTag("editorial", "Editorial");

		EurekaApplication application = appBuilder.build();

		cfgService.load(application);
	}

}
