/**
 *
 */
package com.eureka.cms.rs.business;

import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Identifier;
import com.google.gson.JsonObject;

/**
 * @author Fincons
 *
 */
public interface PopulateEntityStrategy {
	/**
	 *
	 * @param authToken
	 * @param entity
	 * @param object
	 *
	 * @return
	 */
	public Identifier populateAndSave(String entity, JsonObject object, EurekaUser loggedUser);
}
