/**
 *
 */
package com.eureka.cms.core.data.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eureka.cms.core.cfg.bean.entity.FieldDescriptor;
import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.core.data.repository.EurekaDaoSupport;
import com.eureka.cms.core.data.repository.EntityRepository;
import com.eureka.cms.core.data.repository.RepositorySupport;
import com.eureka.commons.bean.LabelValueBean;

/**
 * @author User
 *
 */
public class EntityRepositoryImpl extends EurekaDaoSupport implements EntityRepository {

	private static final Logger logger = LoggerFactory.getLogger(EntityRepositoryImpl.class);

	@Override
	public Object findById(Class<? extends Identifier> target, Serializable id) {
		if (isSetFetchProfile() && isToInclude(target)){
			logger.debug("{} method {} enable Fetch Profile {}", new Object[] {this.getClass().getSimpleName(), "findById", getFetchProfileName()});
			currentSession().enableFetchProfile(getFetchProfileName());
		}
		try {
			return currentSession().get(target, id);
		} catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
	}

	@Override
	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<? extends Identifier> findAll(Class<? extends Identifier> target, boolean excludeLogicalDeleted) {
		if (isSetFetchProfile() && isToInclude(target)){
			logger.debug("{} method {} enable Fetch Profile {}", new Object[] {this.getClass().getSimpleName(), "findAll", getFetchProfileName()});
			currentSession().enableFetchProfile(getFetchProfileName());
		}
		Criteria criteria = currentSession().createCriteria(target);
		if (excludeLogicalDeleted){
			criteria = RepositorySupport.excludeEntityDeleted(criteria);
		}
		// remove duplicate row for M-N relation
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		try {
			return criteria.list();
		} catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LabelValueBean> findAll(Class<? extends Identifier> target, boolean excludeLogicalDeleted, List<FieldDescriptor> fields) {
		if (isSetFetchProfile() && isToInclude(target)){
			logger.debug("{} method {} enable Fetch Profile {}", new Object[] {this.getClass().getSimpleName(), "findAll", getFetchProfileName()});
			currentSession().enableFetchProfile(getFetchProfileName());
		}
		Criteria criteria = currentSession().createCriteria(target);
		if (excludeLogicalDeleted){
			criteria = RepositorySupport.excludeEntityDeleted(criteria);
		}

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.id());
		for (FieldDescriptor p : fields) {
			projList.add(Projections.property(p.getName()));
		}
		criteria.setProjection(projList);

		List<Object[]> res = null;
		try {
			res = criteria.list();
		} catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}

		List<LabelValueBean> result = new ArrayList<LabelValueBean>(res.size());
		for (Object[] r : res) {
			Long id = (Long) r[0];
			String label = StringUtils.join(r, " ", 1, r.length);
			result.add(new LabelValueBean(label, id));
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<? extends Identifier> findAllIn(Class<? extends Identifier> target, boolean excludeLogicalDeleted, String property, Collection<Serializable> in) {
		if (isSetFetchProfile() && isToInclude(target)){
			logger.debug("{} method {} enable Fetch Profile {}", new Object[] {this.getClass().getSimpleName(), "findAllIn", getFetchProfileName()});
			currentSession().enableFetchProfile(getFetchProfileName());
		}
		Criteria criteria = currentSession().createCriteria(target);
		if (excludeLogicalDeleted){
			criteria = RepositorySupport.excludeEntityDeleted(criteria);
		}
		if (CollectionUtils.isNotEmpty(in)){
			criteria.add(Restrictions.in(property, in));
		}
		try {
			return criteria.list();
		} catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
	}

}
