/**
 *
 */
package com.eureka.cms.core.service.data.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.Group;
import com.eureka.cms.core.data.repository.GroupRepository;
import com.eureka.cms.core.service.data.AbstractModelService;
import com.eureka.cms.core.service.data.GroupService;
import com.google.common.base.Optional;

/**
 * @author mmazzilli
 *
 */
@Transactional
public class GroupServiceImpl extends AbstractModelService<Group> implements GroupService {

	private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

	public GroupServiceImpl(PagingAndSortingRepository<Group, Serializable> repository) {
		super(repository);
	}

	@Override
	public Optional<Group> findByName(String name) {
		try {
			Group group = ((GroupRepository) repository).findByName(name);
			return Optional.fromNullable(group);
		} catch (DataRepositoryException dae){
			logger.error(dae.getMessage(), dae);
			return Optional.absent();
		}
	}

}


