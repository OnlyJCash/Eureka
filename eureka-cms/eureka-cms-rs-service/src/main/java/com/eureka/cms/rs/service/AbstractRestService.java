/**
 *
 */
package com.eureka.cms.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.eureka.cms.core.cfg.bean.entity.EntityDescriptor;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.core.service.cfg.ConfigurationService;
import com.eureka.cms.core.service.data.EntityService;
import com.eureka.cms.rs.service.response.Response;

/**
 * @author User
 *
 */
public abstract class AbstractRestService {

	@Autowired
	protected EntityService entityService;

	@Autowired
	protected ConfigurationService configuration;

	/**
	 *
	 * @param body
	 * @return
	 */
	public ResponseEntity<Response<Object>> buildSuccess(Object body){
		return ResponseEntity.ok().body(Response.ok().withBody(body));
	}

	/**
	 *
	 * @param name
	 *
	 * @return
	 */
	protected EntityDescriptor getEntity(String name){
		return configuration.getConfiguration().getEntity(name);
	}

	/**
	 *
	 * @param className
	 *
	 * @return
	 */
	protected EntityDescriptor getEntity(Class<Identifier> className){
		return configuration.getConfiguration().getEntityByClass(className);
	}


	protected EurekaUser getLoggedUser(String authToken){
		// TODO
		return new EurekaUser();
	}
}
