/**
 *
 */
package com.eureka.cms.core.service.data.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.cms.core.common.DefaultUserRoles;
import com.eureka.cms.core.common.Properties;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Group;
import com.eureka.cms.core.data.repository.UserModelRepository;
import com.eureka.cms.core.service.data.AbstractModelService;
import com.eureka.cms.core.service.data.EurekaUserService;
import com.eureka.cms.core.service.data.GroupService;
import com.eureka.commons.aop.logging.Loggable;
import com.eureka.commons.aop.logging.Loggable.Level;
import com.eureka.commons.security.SecurityToken;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
@Transactional
public class EurekaUserServiceImpl extends AbstractModelService<EurekaUser> implements EurekaUserService {

	private static final Logger logger = LoggerFactory.getLogger(EurekaUserServiceImpl.class);

	@Autowired
	private GroupService groupService;

	public EurekaUserServiceImpl(PagingAndSortingRepository<EurekaUser, Serializable> repository) {
		super(repository);
	}

	@Override
	@Loggable(level=Level.INFO)
	public Optional<EurekaUser> getUserByCredential(String username, String password) {
		try {
			return Optional.fromNullable(((UserModelRepository) repository).findByCredential(username, SecurityToken.get(password)));
		} catch (DataAccessException ex){
			logger.error(ex.getMessage(), ex);
			return Optional.absent();
		}

	}

	@Override
	@Loggable
	public Optional<EurekaUser> generateAdminUserIfNotExists() {
		Optional<EurekaUser> oEurekaAdmin = getUserByCredential(getAsString(Properties.USER_ADMIN_USERNAME), getAsString(Properties.USER_ADMIN_PASSWORD));
		if (!oEurekaAdmin.isPresent()){
			EurekaUser user = new EurekaUser();
			user.setActivationDate(new Date());
			user.setEmail(getAsString(Properties.USER_ADMIN_EMAIL));
			user.setUsername(getAsString(Properties.USER_ADMIN_USERNAME));
			user.setPassword(SecurityToken.get(getAsString(Properties.USER_ADMIN_PASSWORD)));
			user.setGroups(new HashSet<Group>());

			Optional<Group> oGroup = groupService.findByName(DefaultUserRoles.ADMINISTRATOR.getName());
			if (oGroup.isPresent()){
				user.getGroups().add(oGroup.get());
			} else {
				Group g = new Group();
				g.setLabel(getAsString(Properties.GROUP_ADMIN_LABEL));
				g.setName(getAsString(Properties.GROUP_ADMIN_LABEL).toLowerCase());
				g.setEntityAllowed("*");
				user.getGroups().add(g);
			}
			oEurekaAdmin = save(user);
		}
		return oEurekaAdmin;
	}

}
