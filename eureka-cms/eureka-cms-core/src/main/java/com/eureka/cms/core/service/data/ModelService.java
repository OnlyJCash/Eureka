/**
 *
 */
package com.eureka.cms.core.service.data;

import java.io.Serializable;
import java.util.List;

import com.eureka.cms.core.data.model.Identifier;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public interface ModelService<T extends Identifier> {

	/**
	 *
	 * @param id
	 *
	 * @return
	 */
	public Optional<T> getById(Serializable id);

	/**
	 *
	 * @param entity
	 *
	 * @return
	 */
	public Optional<T> save(T entity);

	/**
	 *
	 */
	public Optional<T> saveOrUpdate(T entity);

	/**
	 *
	 * @return
	 */
	public List<T> findAll();

	/**
	 *
	 * @param entity
	 */
	public void delete(T entity);
}
