/**
 *
 */
package com.eureka.cms.core.service.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.core.service.exception.DataServiceException;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
@Transactional(rollbackFor={DataServiceException.class, IllegalArgumentException.class})
public abstract class AbstractModelService<T extends Identifier> extends AbstractCMSProperties implements ModelService<T> {

	protected PagingAndSortingRepository<T, Serializable> repository;

	public AbstractModelService(PagingAndSortingRepository<T, Serializable> repository) {
		this.repository = repository;
	}

	@Override
	public Optional<T> getById(Serializable id) {
		return Optional.fromNullable(repository.findOne(id));
	}

	@Override
	public Optional<T> save(T entity) {
		try {
			return Optional.fromNullable(repository.save(entity));
		} catch (DataRepositoryException ex){
			throw new DataServiceException("Data Service - Exception during save entity: " + ex.getMessage(), ex);
		}
	}

	@Override
	public Optional<T> saveOrUpdate(T entity) {
		try {
			return Optional.of(repository.save(entity));
		} catch (Exception ex){
			throw new DataServiceException("Data Service - Exception during save entity: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<T> findAll() {
		return (List<T>) repository.findAll();
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}

}


