/**
 *
 */
package com.eureka.cms.rs.adapter;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.eureka.cms.core.cfg.bean.EurekaConfiguration;
import com.eureka.cms.core.cfg.bean.TagDescriptor;
import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Group;
import com.eureka.commons.aop.logging.Loggable;

/**
 * @author Fincons
 *
 */
public class SecureAccessEntityImpl implements SecureAccessEntity {

	private static final Logger logger = LoggerFactory.getLogger(SecureAccessEntityImpl.class);

	private static final String REGEX = "regex:";

	@Override
	@Loggable
	public EurekaConfiguration verify(EurekaConfiguration source, EurekaUser user) {
		Assert.notNull(user, "User must be set!");
		EurekaConfiguration configurationByUser = new EurekaConfiguration();

		configurationByUser.setProject(source.getProject());

		for (Iterator<Entry<String, EntityDescriptor>> iterator = source.getEntities().entrySet().iterator(); iterator.hasNext();) {
			Entry<String, EntityDescriptor> entry = iterator.next();
			for (Group group : user.getGroups()) {
				if (group.getEntityAllowed().equals("*")){
					logger.debug("User {} with group {} has grant on all entities", user.getId(), group.getLabel());
					configurationByUser.getEntities().putAll(source.getEntities());
					configurationByUser.getTags().putAll(source.getTags());
					return configurationByUser;
				} else if (isRegex(group) && matches(entry, group)){
					logger.debug("User {} with group {} has grant (by-regex) on entity {}", new Object[]{user.getId(), group.getLabel(), entry.getKey()});
					configurationByUser.addEntity(entry.getValue());
					addTags(configurationByUser, source, entry);
				} else if (group.getEntityAllowed().contains(entry.getKey())){
					logger.debug("User {} with group {} has grant on entity {}", new Object[]{user.getId(), group.getLabel(), entry.getKey()});
					configurationByUser.addEntity(entry.getValue());
					addTags(configurationByUser, source, entry);
				}
			}
		}
		return configurationByUser;
	}

	/**
	 * @param group
	 * @return
	 */
	private boolean isRegex(Group group) {
		return group.getEntityAllowed().startsWith(REGEX);
	}

	/**
	 * @param entry
	 * @param group
	 * @return
	 */
	private boolean matches(Entry<String, EntityDescriptor> entry, Group group) {
		String regex = group.getEntityAllowed().substring(REGEX.length());
		return matches(entry.getKey(), regex);
	}

	/**
	 * @param entry
	 * @param regex
	 * @return
	 */
	private boolean matches(String entityName, String regex) {
		return Pattern.compile(regex).matcher(entityName).matches();
	}

	@Override
	public boolean canAccess(EurekaUser loggedUser, String entityName) {
		Assert.hasText(entityName, "Entity Name must be set to perform check!");
		for (Group group : loggedUser.getGroups()) {
			if (group.getEntityAllowed().equals("*")){
				return true;
			}else if (isRegex(group) && matches(entityName, group.getEntityAllowed())){
				return true;
			} else if (group.getEntityAllowed().contains(entityName)){
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param configurationByUser
	 * @param source
	 * @param entry
	 */
	private void addTags(EurekaConfiguration configurationByUser, EurekaConfiguration source, Entry<String, EntityDescriptor> entry) {
		logger.trace("START - AccessSecurityToEntityImpl - addTags");
		Set<String> tags = entry.getValue().getTags();
		for (String tag : tags) {
			if (!configurationByUser.getTags().containsKey(tag)){
				TagDescriptor tagDescriptor = source.getTags().get(tag);
				configurationByUser.getTags().put(tag, tagDescriptor);
			}
		}
		logger.trace("END - AccessSecurityToEntityImpl - addTags");
	}

}
