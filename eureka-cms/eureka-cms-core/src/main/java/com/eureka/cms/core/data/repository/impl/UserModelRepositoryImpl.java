/**
 *
 */
package com.eureka.cms.core.data.repository.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.repository.ModelRepository;
import com.eureka.cms.core.data.repository.UserModelRepository;

/**
 * @author User
 *
 */
public class UserModelRepositoryImpl extends ModelRepository<EurekaUser> implements UserModelRepository {

	public UserModelRepositoryImpl() {
		super(EurekaUser.class);
	}

	@Override
	public EurekaUser findByCredential(String username, String password) {
		Criteria q = criteria().add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password));
		q.add(Restrictions.isNotNull("activationDate")).add(Restrictions.le("activationDate", new Date()));
		try {
			return (EurekaUser) q.uniqueResult();
		} catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
	}


}
