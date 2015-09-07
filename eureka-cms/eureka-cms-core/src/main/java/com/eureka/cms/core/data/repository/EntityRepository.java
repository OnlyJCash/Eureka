package com.eureka.cms.core.data.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.eureka.cms.core.cfg.bean.entity.FieldDescriptor;
import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.commons.bean.LabelValueBean;

/**
 *
 * @author mmazzilli
 *
 */
public interface EntityRepository {

	/**
	 *
	 * @param target Persist Class
	 * @param id Identifier
	 *
	 * @return Record
	 *
	 * @throws DataRepositoryException
	 */
	public Object findById(Class<? extends Identifier> target, Serializable id) throws DataRepositoryException;

	/**
	 *
	 * @param entity
	 *
	 * @throws DataRepositoryException
	 */
	public void saveOrUpdate(Object entity) throws DataRepositoryException;

	/**
	 *
	 * @param target
	 * @param excludeLogicalDeleted
	 *
	 * @return
	 *
	 * @throws DataRepositoryException
	 */
	public List<? extends Identifier> findAll(Class<? extends Identifier> target, boolean excludeLogicalDeleted) throws DataRepositoryException;

	/**
	 *
	 * @param target
	 * @param excludeLogicalDeleted
	 * @param fields
	 *
	 * @return
	 *
	 * @throws DataRepositoryException
	 */
	public List<LabelValueBean> findAll(Class<? extends Identifier> target, boolean excludeLogicalDeleted, List<FieldDescriptor> fields) throws DataRepositoryException;

	/**
	 *
	 * @param target
	 * @param excludeLogicalDeleted
	 * @param property
	 * @param in
	 *
	 * @return
	 *
	 * @throws DataRepositoryException
	 */
	public List<? extends Identifier> findAllIn(Class<? extends Identifier> target, boolean excludeLogicalDeleted, String property, Collection<Serializable> in) throws DataRepositoryException;
}
