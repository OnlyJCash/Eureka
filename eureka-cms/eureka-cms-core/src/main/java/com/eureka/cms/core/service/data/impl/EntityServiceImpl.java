/**
 *
 */
package com.eureka.cms.core.service.data.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.core.data.repository.EntityRepository;
import com.eureka.cms.core.service.cfg.ConfigurationService;
import com.eureka.cms.core.service.data.EntityService;
import com.eureka.cms.core.service.exception.DataServiceException;
import com.eureka.commons.aop.logging.Loggable;
import com.eureka.commons.bean.LabelValueBean;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

/**
 * @author User
 *
 */
@Transactional(rollbackFor={DataServiceException.class, IllegalArgumentException.class})
public class EntityServiceImpl implements EntityService {

	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private EntityRepository repository;

	@Override
	@Loggable
	public Optional<Object> getById(String entityName, Serializable id) {
		return Optional.fromNullable(repository.findById(getPersistClass(entityName), id));
	}

	@Override
	@Loggable
	public void saveOrUpdate(Identifier entity, EurekaUser loggedUser) {
		repository.saveOrUpdate(entity);
	}

	@Override
	@Loggable
	public List<? extends Identifier> findAll(String entityName) {
		EntityDescriptor entity = getEntityDescriptor(entityName);
		return repository.findAll(entity.getTarget(), entity.isHasMetadata());
	}

	@Override
	@Loggable
	public List<LabelValueBean> getProjections(String entityName) {
		EntityDescriptor entity = getEntityDescriptor(entityName);
		return repository.findAll(entity.getTarget(), entity.isHasMetadata(), entity.getVisibleInRelation());
	}

	@Override
	@Loggable
	public List<? extends Identifier> findAllWithIds(String entityName, Collection<Long> ids) {
		EntityDescriptor entity = getEntityDescriptor(entityName);
		return repository.findAllIn(entity.getTarget(), entity.isHasMetadata(), "id", Lists.<Serializable>newArrayList(ids));
	}

	protected Class<? extends Identifier> getPersistClass(String entityName){
		return getEntityDescriptor(entityName).getTarget();
	}

	protected EntityDescriptor getEntityDescriptor(String entityName){
		return configurationService.getEntity(entityName);
	}
}
