/**
 *
 */
package com.eureka.cms.rs.service.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.rs.business.PopulateEntityStrategy;
import com.eureka.cms.rs.common.Constants;
import com.eureka.cms.rs.service.AbstractRestService;
import com.eureka.cms.rs.service.response.Response;
import com.google.common.base.Optional;
import com.google.gson.JsonObject;

/**
 * @author User
 *
 */
@RestController
@RequestMapping("/console/{version}/resource")
public class EntityRestServiceImpl extends AbstractRestService {

	@Autowired
	private PopulateEntityStrategy populateEntityStrategy;

	@RequestMapping(value="/{entity}/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<Object>> getById(@PathVariable("version") String version,
			@PathVariable("entity") String entity, @PathVariable("id") Long id) {

		Optional<Object> oRecord = entityService.getById(entity, id);

		if (oRecord.isPresent()){
			return buildSuccess(oRecord.get());
		}

		// Resource not found
		Response<Object> body = Response.ok().addHeaderMessage(String.valueOf(HttpStatus.NOT_FOUND.value()), "Resource not found");
		return ResponseEntity.ok().body(body);
	}

	/* (non-Javadoc)
	 * @see com.eureka.cms.rs.service.entity.EntityRestService#query(java.lang.String)
	 */
	@RequestMapping(value="/{entity}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Response<Object>> query(@PathVariable("version") String version, @PathVariable("entity") String entity) {
		List<? extends Identifier> result = entityService.findAll(entity);
		return buildSuccess(result);
	}

	/* (non-Javadoc)
	 * @see com.eureka.cms.rs.service.entity.EntityRestService#save(java.lang.String)
	 */
	@RequestMapping(value="/{entity}", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Response<Object>> save(@PathVariable("version") String version, @RequestHeader(Constants.HEADER_AUTHORIZATION_TOKEN) String authToken, @PathVariable("entity") String entity,  @RequestBody JsonObject object) {
		Identifier record = populateEntityStrategy.populateAndSave(entity, object, getLoggedUser(authToken));
		return ResponseEntity.ok().body(Response.ok().withBody(record));
	}

	@RequestMapping(value="/projection/{entity}", method=RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Response<Object>> projection(@PathVariable("version") String version, @PathVariable("entity") String entity, JsonObject projections) {
		return ResponseEntity.ok().body(Response.ok().withBody(entityService.getProjections(entity)));
	}

}
