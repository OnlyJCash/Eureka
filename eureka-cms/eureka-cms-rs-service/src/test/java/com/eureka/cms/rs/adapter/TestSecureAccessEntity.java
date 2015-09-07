/**
 *
 */
package com.eureka.cms.rs.adapter;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.cfg.bean.EurekaConfiguration;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Group;
import com.eureka.cms.rs.SpringContextInitialized;
import com.example.model.Article;
import com.example.model.Category;

/**
 * @author mmazzilli
 *
 */
public class TestSecureAccessEntity extends SpringContextInitialized {


	@Autowired
	private MockConfigurationService serviceMock;
	@Autowired
	private SecureAccessEntity secureAccessEntity;

	@Before
	public void setUp(){
		serviceMock.load();
	}

	@Test
	public void testVerify_success_IsAdmin() throws Exception{
		EurekaConfiguration ec = serviceMock.getConfiguration();
		EurekaUser user = getUser("*");
		EurekaConfiguration ecByUser = secureAccessEntity.verify(ec, user);
		Assert.assertEquals(ec.getEntities().size(), ecByUser.getEntities().size());
	}

	@Test
	public void testVerify_success_GrantAll() throws Exception{
		EurekaConfiguration ec = serviceMock.getConfiguration();
		EurekaUser user = getUser("article,category");
		EurekaConfiguration ecByUser = secureAccessEntity.verify(ec, user);
		Assert.assertEquals(2, ecByUser.getEntities().size());
	}

	@Test
	public void testVerify_success_GrantOnArticle() throws Exception{
		EurekaConfiguration ec = serviceMock.getConfiguration();
		EurekaUser user = getUser("article");
		EurekaConfiguration ecByUser = secureAccessEntity.verify(ec, user);
		Assert.assertTrue(ecByUser.getEntities().size()==1);
	}

	@Test
	public void testVerify_success_GrantOnArticleByRegex() throws Exception{
		EurekaConfiguration ec = serviceMock.getConfiguration();
		// All entity starts art
		EurekaUser user = getUser("regex:art.*");
		EurekaConfiguration ecByUser = secureAccessEntity.verify(ec, user);
		Assert.assertTrue(ecByUser.getEntities().size()==1);
	}

	@Test
	public void testVerify_success_GrantAllWithoutEurekaCmsEntitiesWithIncludes() throws Exception{
		EurekaConfiguration ec = serviceMock.getConfiguration();
		// All entity starts art
		EurekaUser user = getUser("regex:(.*cle|cat.*)");
		EurekaConfiguration ecByUser = secureAccessEntity.verify(ec, user);
		Assert.assertNotNull(ecByUser.getEntityByClass(Article.class));
		Assert.assertNotNull(ecByUser.getEntityByClass(Category.class));
		Assert.assertNull(ecByUser.getEntityByClass(EurekaUser.class));
		Assert.assertNull(ecByUser.getEntityByClass(Group.class));
	}

	// @Test
	public void testVerify_success_GrantAllWithoutEurekaCmsEntitiesWithExcludes() throws Exception{
		EurekaConfiguration ec = serviceMock.getConfiguration();
		// All entity starts art
		EurekaUser user = getUser("regex:^(?!(group|eurekauser))$");
		EurekaConfiguration ecByUser = secureAccessEntity.verify(ec, user);
		Assert.assertEquals(2, ecByUser.getEntities().size());
		Assert.assertNull(ecByUser.getEntityByClass(EurekaUser.class));
		Assert.assertNull(ecByUser.getEntityByClass(Group.class));
		Assert.assertNotNull(ecByUser.getEntityByClass(Article.class));
		Assert.assertNotNull(ecByUser.getEntityByClass(Category.class));
	}

	private EurekaUser getUser(String entityAllowed) {
		EurekaUser user = new EurekaUser();
		user.setId(1L);
		user.setGroups(new HashSet<Group>());
		Group group = new Group();
		group.setId(1L);
		group.setLabel(entityAllowed);
		group.setEntityAllowed(entityAllowed);
		user.getGroups().add(group);
		return user;
	}
}
