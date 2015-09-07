/**
 *
 */
package com.eureka.cms.core.data.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.Group;
import com.eureka.cms.core.data.repository.GroupRepository;
import com.eureka.cms.core.data.repository.ModelRepository;

/**
 * @author mmazzilli
 *
 */
public class GroupRepositoryImpl extends ModelRepository<Group> implements GroupRepository {

	public GroupRepositoryImpl() {
		super(Group.class);
	}

	@Override
	public Group findByName(String name) throws DataRepositoryException {
		Group group = null;
		Criteria query = criteria().add(Restrictions.eq("name", name));
		try {
			group = (Group) query.uniqueResult();
		} catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
		return group;
	}

}
