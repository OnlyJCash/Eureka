/**
 *
 */
package com.eureka.cms.rs;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author mmazzilli
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-eureka-cms-application-context.xml")
public abstract class SpringContextInitialized {

	@Autowired
	protected ApplicationContext context;

}
