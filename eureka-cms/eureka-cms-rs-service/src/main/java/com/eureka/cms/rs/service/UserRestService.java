package com.eureka.cms.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.service.data.AuthenticationService;
import com.eureka.cms.rs.common.Constants;
import com.eureka.cms.rs.service.response.Response;
import com.google.common.base.Optional;

/**
 *
 * @author mmazzilli
 *
 */
@RestController
@RequestMapping(value="/console/{version}/user")
public class UserRestService extends AbstractRestService {

	@Autowired
	private AuthenticationService authService;

	@RequestMapping(value="/profile/me", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<Object>> me(@PathVariable("version") String version, @RequestHeader(Constants.HEADER_AUTHORIZATION_TOKEN) String authToken){
		Optional<EurekaUser> oAuthorizedUser = authService.getUserByToken(authToken);
		if (oAuthorizedUser.isPresent()){
			return buildSuccess(oAuthorizedUser.get());
		}
		return ResponseEntity.ok(Response.<Object>fail().withHeaderCode("USER_BY_TOKEN_NOT_FOUND"));
	}
}
