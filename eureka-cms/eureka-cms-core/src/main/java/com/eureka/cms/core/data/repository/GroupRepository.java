/**
 *
 */
package com.eureka.cms.core.data.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.eureka.cms.core.data.exception.DataRepositoryException;
import com.eureka.cms.core.data.model.Group;

/**
 * @author User
 *
 */
public interface GroupRepository extends PagingAndSortingRepository<Group, Serializable> {

	/**
	 *
	 * @param group name
	 *
	 * @return Group
	 */
	public Group findByName(String name) throws DataRepositoryException;


}
