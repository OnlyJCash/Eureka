/**
 *
 */
package com.eureka.cms.core.service.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.core.service.exception.DataServiceException;
import com.eureka.commons.bean.LabelValueBean;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public interface EntityService {

	public Optional<Object> getById(String entityName, Serializable id) throws DataServiceException;

	public void saveOrUpdate(Identifier entity, EurekaUser loggedUser) throws DataServiceException;

	public List<? extends Identifier> findAll(String entityName) throws DataServiceException;

	public List<LabelValueBean> getProjections(String entityName) throws DataServiceException;

	public List<? extends Identifier> findAllWithIds(String entityName, Collection<Long> ids) throws DataServiceException;
}
