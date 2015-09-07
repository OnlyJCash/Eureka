/**
 *
 */
package com.eureka.cms.rs.service;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.cms.rs.adapter.ConfigurationAdapter;
import com.eureka.cms.rs.adapter.bean.cfg.ConfigurationBean;
import com.eureka.cms.rs.common.Constants;
import com.eureka.cms.rs.service.response.Response;

/**
 * @author mmazzilli
 *
 */
@RestController
@RequestMapping(value="/console/{version}")
public class ConfigurationRestServiceImpl extends AbstractRestService {

	@Autowired
	private ConfigurationAdapter adapter;

	@RequestMapping(value="/configuration", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<ConfigurationBean>> getConfiguration(@PathVariable("version") String version, @NotBlank @RequestHeader(value=Constants.HEADER_AUTHORIZATION_TOKEN) String authToken) {
		ConfigurationBean configuration = adapter.getConfigurationAdapted(authToken);
		Response<ConfigurationBean> entiy = Response.<ConfigurationBean>ok().withBody(configuration);
		return ResponseEntity.ok().body(entiy);
	}

}
