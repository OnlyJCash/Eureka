/**
 *
 */
package com.eureka.cms.core.service.data;

import com.eureka.cms.core.data.model.Group;
import com.google.common.base.Optional;

/**
 * @author User
 *
 */
public interface GroupService extends ModelService<Group> {

	public Optional<Group> findByName(String name);

}
