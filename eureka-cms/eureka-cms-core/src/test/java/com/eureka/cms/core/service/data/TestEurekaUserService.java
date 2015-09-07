/**
 *
 */
package com.eureka.cms.core.service.data;

import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.SpringContextInitialized;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Group;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public class TestEurekaUserService extends SpringContextInitialized {

	@Autowired
	protected EurekaUserService userService;


	@Test
	public void testCRUDonUser_success(){
		Optional<EurekaUser> oRes = userService.saveOrUpdate(createUser(1));

		Assert.assertTrue(oRes.isPresent());
		Assert.assertTrue(oRes.get().getId() > 0);

		System.out.println(oRes.get());

		Optional<EurekaUser> oResById = userService.getById(oRes.get().getId());

		Assert.assertTrue(oResById.isPresent());
		Assert.assertTrue(oResById.get().getId() > 0);
		Assert.assertEquals(oRes.get().getId(), oResById.get().getId());


		oRes = userService.saveOrUpdate(createUser(2));
		oRes = userService.saveOrUpdate(createUser(3));

		List<EurekaUser> users = userService.findAll();

		Assert.assertNotNull(users);
		Assert.assertFalse(users.isEmpty());
		System.out.println(users.size());
		// Assert.assertTrue(users.size() == 3);

		userService.delete(users.get(2));

		users = userService.findAll();

		Assert.assertNotNull(users);
		Assert.assertFalse(users.isEmpty());
		// Assert.assertTrue(users.size() == 2)
	}

	@Test
	public void testGetUserByCredential_success(){
		Optional<EurekaUser> userByCredential = userService.getUserByCredential("admin", "eureka");
		Assert.assertTrue(userByCredential.isPresent());
	}


	/**
	 * @return
	 */
	private EurekaUser createUser(int i) {
		EurekaUser u = new EurekaUser();
		u.setUsername("mima"+ i);
		u.setEmail("example@example.com");
		Group g = new Group();
		g.setEntityAllowed("*");
		g.setName("administrator");
		u.setGroups(new HashSet<Group>());
		u.getGroups().add(g);
		return u;
	}
}
