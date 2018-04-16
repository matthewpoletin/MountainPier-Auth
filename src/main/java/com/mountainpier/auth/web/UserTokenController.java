package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.domain.UserToken;
import com.mountainpier.auth.exception.InvalidTokenException;
import com.mountainpier.auth.exception.TokenExpireException;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.service.UserTokenService;
import com.mountainpier.auth.web.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(UserTokenController.userTokenBaseURI)
public class UserTokenController {
	
	static final String userTokenBaseURI = "/api/auth";
	
	private final UserTokenService userTokenService;
	
	@Autowired
	public UserTokenController(UserTokenService userTokenService) {
		this.userTokenService = userTokenService;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public UserTokenResponse createToken(@Valid @RequestBody UserTokenInitialRequest tokenRequest)
			throws UserCredentialsException {
		UserToken userToken = this.userTokenService.create(tokenRequest);
		return new UserTokenResponse(userToken);
	}
	
	@RequestMapping(value = "/token", method = RequestMethod.DELETE)
	public void deleteToken(@Valid @RequestBody UserTokenRequest userTokenRequest) {
		this.userTokenService.delete(userTokenRequest);
	}
	
	@RequestMapping(value = "/token/check", method = RequestMethod.POST)
	public UserResponse checkToken(@Valid @RequestBody UserTokenRequest userTokenRequest)
			throws TokenExpireException, InvalidTokenException {
		User user = this.userTokenService.checkAccessToken(userTokenRequest);
		return new UserResponse(user);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/token/refresh", method = RequestMethod.POST)
	public UserTokenResponse refreshToken(@Valid @RequestBody UserTokenRefreshRequest refreshTokenRequest)
			throws TokenExpireException, InvalidTokenException {
		UserToken userToken = this.userTokenService.refresh(refreshTokenRequest);
		return new UserTokenResponse(userToken);
	}
	
}
