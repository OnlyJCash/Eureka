/**
 *
 */
package com.eureka.cms.rs.service;

import java.util.HashMap;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.cms.core.data.model.SessionToken;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.service.data.AuthenticationService;
import com.eureka.cms.core.service.data.EurekaUserService;
import com.eureka.cms.rs.common.Constants;
import com.eureka.cms.rs.service.response.Response;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

/**
 * @author Fincons
 *
 */
@Validated
@RestController
@RequestMapping(value="/{version}/auth")
public class AuthenticationRestServiceImpl extends AbstractRestService {

	@Autowired
	private EurekaUserService userService;
	@Autowired
	private AuthenticationService authService;

	/* (non-Javadoc)
	 * @see com.eureka.cms.rs.service.AuthenticationRestService#login(java.lang.String, java.lang.String)
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<Object>> auth(@PathVariable("version") String version,
			@NotBlank @RequestParam(value="usr") String username, @NotBlank @RequestParam(value="pwd") String password) {

		Optional<EurekaUser> oUser = userService.getUserByCredential(username, password);
		if (oUser.isPresent()){
			SessionToken authToken = authService.create(oUser.get());

			HashMap<String, Object> body = Maps.<String, Object>newHashMap();
			body.put("id", oUser.get().getId());
			body.put("authToken", authToken.getToken());
			body.put("experied", authToken.getExpiredAt());
			return buildSuccess(body);
		}

		Response<Object> fail = Response.fail().withHeaderCode("BAD_LOGIN").withHeaderMessage("User not found");
		return ResponseEntity.ok(fail);
	}

	@RequestMapping(value="/logout", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<String>> logout(@PathVariable("version") String version, @RequestHeader(Constants.HEADER_AUTHORIZATION_TOKEN) String authToken){

		boolean result = authService.invalidateToken(authToken);

		if (result){
			return ResponseEntity.ok(Response.<String>ok().withBody("Invalidate token"));
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.<String>fail().withBody("Token not valid!"));
	}

	@RequestMapping(value="/refresh", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<Object>> refresh(@PathVariable("version") String version,
			@RequestHeader(Constants.HEADER_AUTHORIZATION_TOKEN) String token, @RequestParam("id") Long userId){

		Optional<SessionToken> oAuthToken = authService.refresh(token, userId);
		if (oAuthToken.isPresent()){
			SessionToken authToken = oAuthToken.get();
			HashMap<String, Object> body = Maps.<String, Object>newHashMap();
			body.put("authToken", authToken.getToken());
			body.put("experied", authToken.getExpiredAt());
			return buildSuccess(body);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.<Object>fail().withBody("Token not valid!"));
	}

}
