package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.OAuthToken;
import com.mountainpier.auth.exception.AppCredentialsException;
import com.mountainpier.auth.exception.InvalidTokenException;
import com.mountainpier.auth.exception.TokenExpireException;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.service.OAuthTokenService;
import com.mountainpier.auth.web.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(OAuthTokenController.oauthBaseURI)
public class OAuthTokenController {
	
	static final String oauthBaseURI = "/api/auth";
	
	private final OAuthTokenService oAuthTokenService;
	
	@Autowired
	public OAuthTokenController(OAuthTokenService oAuthTokenService) {
		this.oAuthTokenService = oAuthTokenService;
	}
	
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/oauth/login", method = RequestMethod.POST)
	public OAuthTokenCodeResponse createToken(@Valid @RequestBody OAuthTokenInitialRequest tokenRequest) throws UserCredentialsException {
		OAuthToken token = this.oAuthTokenService.create(tokenRequest);
		return new OAuthTokenCodeResponse(token.getAppCode(), tokenRequest.getRedirectUrl());
	}
	
	@RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
	public OAuthTokenResponse getToken(@Valid @RequestBody OAuthTokenCodeRequest codeRequest) throws AppCredentialsException {
		return new OAuthTokenResponse(this.oAuthTokenService.findByAppCode(codeRequest));
	}
	
	@RequestMapping(value = "/oauth/token/refresh", method = RequestMethod.POST)
	public OAuthTokenResponse getToken(@Valid @RequestBody OAuthTokenRefreshRequest refreshRequest) throws Exception {
		return new OAuthTokenResponse(this.oAuthTokenService.refresh(refreshRequest));
	}
	
	@RequestMapping(value = "/oauth/token/check", method = RequestMethod.POST)
	public UserResponse checkToken(@Valid @RequestBody OAuthTokenRequest tokenRequest) throws TokenExpireException, InvalidTokenException {
		return new UserResponse(this.oAuthTokenService.checkAccessToken(tokenRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/oauth/token", method = RequestMethod.DELETE)
	public void deleteToken(@Valid @RequestBody OAuthTokenRequest tokenRequest) throws TokenExpireException, InvalidTokenException {
		this.oAuthTokenService.toStopList(tokenRequest);
	}
	
}
