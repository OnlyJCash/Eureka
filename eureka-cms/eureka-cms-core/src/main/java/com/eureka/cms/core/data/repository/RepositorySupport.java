package com.eureka.cms.core.data.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.eureka.cms.core.common.Constants;

/**
 *
 * @author User
 *
 */
public abstract class RepositorySupport {

	/**
	 *
	 * @param criteria
	 * @return
	 */
	public static Criteria excludeEntityDeleted(Criteria criteria){
		criteria.createCriteria(Constants.EUREKA_MODEL_AUDIT_FIELD_NAME, Constants.EUREKA_MODEL_AUDIT_FIELD_NAME, JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.or(Restrictions.isNull(Constants.EUREKA_MODEL_AUDIT_FIELD_NAME + ".delete"), Restrictions.eq(Constants.EUREKA_MODEL_AUDIT_FIELD_NAME + ".delete", Boolean.FALSE)));
		return criteria;
	}
}
