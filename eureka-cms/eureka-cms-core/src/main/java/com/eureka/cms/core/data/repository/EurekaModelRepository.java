/**
 *
 */
package com.eureka.cms.core.data.repository;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.eureka.cms.core.common.Constants;
import com.eureka.cms.core.data.model.EurekaModel;
import com.google.common.collect.Lists;

/**
 * @author User
 *
 */
public abstract class EurekaModelRepository<T extends EurekaModel> extends ModelRepository<T> {

	public EurekaModelRepository(Class<T> model) {
		super(model);
	}

	/**
	 * Exclude Entity logical deleted
	 * @return
	 */
	protected Criteria excludeEntityDeleted(Criteria criteria){
		criteria.createCriteria(Constants.EUREKA_MODEL_AUDIT_FIELD_NAME, Constants.EUREKA_MODEL_AUDIT_FIELD_NAME, JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.or(Restrictions.isNull(Constants.EUREKA_MODEL_AUDIT_FIELD_NAME + ".delete"), Restrictions.eq(Constants.EUREKA_MODEL_AUDIT_FIELD_NAME + ".delete", Boolean.FALSE)));
		return criteria;
	}

	@Override
	public long count() {
		Number count = (Number) excludeEntityDeleted(criteria()).setProjection(Projections.rowCount()).uniqueResult();
		return count != null ? count.longValue() : 0;
	}

	@SuppressWarnings("unchecked")
	public Iterable<T> findAllExcludeDeleted() {
		return excludeEntityDeleted(criteria()).list();
	}

	@SuppressWarnings("unchecked")
	public Iterable<T> findAllExcludeDeleted(Iterable<Serializable> ids) {
		return excludeEntityDeleted(criteria()).add(Restrictions.in("id", Lists.newArrayList(ids))).list();
	}
}
