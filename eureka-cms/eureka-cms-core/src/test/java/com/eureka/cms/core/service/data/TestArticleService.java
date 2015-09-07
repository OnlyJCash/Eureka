/**
 *
 */
package com.eureka.cms.core.service.data;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.SpringContextInitialized;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Group;
import com.eureka.cms.core.data.model.Audit;
import com.example.model.Article;


/**
 * @author User
 *
 */
public class TestArticleService extends SpringContextInitialized {

	@Autowired
	protected EntityService entityService;
	@Autowired
	protected EurekaUserService userService;

	@Test
	public void testCRUDonArticle_success(){
		EurekaUser user = new EurekaUser();
		user.setUsername("username");
		Group g = new Group();
		g.setEntityAllowed("*");
		g.setName("administrator");
		user.setGroups(new HashSet<Group>());
		user.getGroups().add(g);

		userService.saveOrUpdate(user);

		Article a = new Article();
		a.setTitle("Ciao");

		a.setAudit(new Audit());
		a.getAudit().setCreatedBy(user);
		// entityService.saveOrUpdate(a.getRowMetadata(), null);
		entityService.saveOrUpdate(a, user);

		@SuppressWarnings("unchecked")
		List<Article> findAll = (List<Article>) entityService.findAll("article");
		System.out.println(findAll);
	}
}
