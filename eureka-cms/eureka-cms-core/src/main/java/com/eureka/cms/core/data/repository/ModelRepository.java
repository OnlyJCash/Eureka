/**
 *
 */
package com.eureka.cms.core.data.repository;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.core.service.data.aop.RowMetadataJoinPoint;
import com.google.common.collect.Lists;

/**
 * @author User
 *
 */
public abstract class ModelRepository<T extends Identifier> extends EurekaDaoSupport implements PagingAndSortingRepository<T, Serializable> {

	protected final Class<T> target;

	public ModelRepository(Class<T> model) {
		this.target = model;
	}

	/**
	 *
	 * @return Criteria exclude logical delete
	 */
	protected Criteria criteria(){
		if (isSetFetchProfile()){
			currentSession().enableFetchProfile(getFetchProfileName());
		}
		return currentSession().createCriteria(this.target);
	}

	@Override
	public <S extends T> S save(S entity) {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (DataAccessException dae){
			throw new DataRepositoryException(dae.getMessage(), dae);
		}
		return entity;
	}

	@Override
	public long count() {
		Criteria crit = criteria().setProjection(Projections.rowCount());
		try {
			Number count = (Number) crit.uniqueResult();
			return count != null ? count.longValue() : 0;
		} catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
	}

	@Override
	public void delete(T entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch (DataAccessException dae){
			throw new DataRepositoryException(dae.getMessage(), dae);
		}
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		try{
			getHibernateTemplate().deleteAll(Lists.newArrayList(entities));
		} catch (DataAccessException dae){
			throw new DataRepositoryException(dae.getMessage(), dae);
		}
	}

	@Override
	public void delete(Serializable id) {
		try {
			getHibernateTemplate().delete(findOne(id));
		} catch (DataAccessException dae){
			throw new DataRepositoryException(dae.getMessage(), dae);
		}
	}

	@Override
	public void deleteAll() {
		try {
			getHibernateTemplate().deleteAll(Lists.newArrayList(findAll()));
		} catch (DataAccessException dae){
			throw new DataRepositoryException(dae.getMessage(), dae);
		}
	}

	@Override
	public boolean exists(Serializable id) {
		return findOne(id) != null;
	}

	@Override
	public T findOne(Serializable id) {
		try {
			return getHibernateTemplate().get(this.target, id);
		} catch (DataAccessException dae){
			throw new DataRepositoryException(dae.getMessage(), dae);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterable<T> findAll() {
		try {
			return criteria().list();
		}  catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterable<T> findAll(Iterable<Serializable> ids) {
		try {
			return criteria().add(Restrictions.in("id", Lists.newArrayList(ids))).list();
		}  catch (HibernateException he){
			throw new DataRepositoryException(he.getMessage(), he);
		}
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RowMetadataJoinPoint
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		ArrayList<S> entityList = Lists.newArrayList(entities);
		for (S s : entityList) {
			getHibernateTemplate().save(s);
		}
		return entities;
	}

}
