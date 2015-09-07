/**
 *
 */
package com.eureka.cms.core.data.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.EurekaUser;

/**
 * @author User
 *
 */
public interface UserModelRepository extends PagingAndSortingRepository<EurekaUser, Serializable> {

	/**
	 * Oss. Restriction on activationDate
	 *
	 * @param username
	 * @param password hashed
	 *
	 * @return Eureka User
	 */
	public EurekaUser findByCredential(String username, String password) throws DataRepositoryException;


}
