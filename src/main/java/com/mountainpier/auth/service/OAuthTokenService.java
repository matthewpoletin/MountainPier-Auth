package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.OAuthToken;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.exception.AppCredentialsException;
import com.mountainpier.auth.exception.InvalidTokenException;
import com.mountainpier.auth.exception.TokenExpireException;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.web.model.OAuthTokenCodeRequest;
import com.mountainpier.auth.web.model.OAuthTokenInitialRequest;
import com.mountainpier.auth.web.model.OAuthTokenRefreshRequest;
import com.mountainpier.auth.web.model.OAuthTokenRequest;

public interface OAuthTokenService {
	
	OAuthToken create(OAuthTokenInitialRequest oAuthTokenRequest) throws UserCredentialsException;
	
	OAuthToken findByAppCode(OAuthTokenCodeRequest oAuthTokenCodeRequest) throws AppCredentialsException;
	
	OAuthToken refresh(OAuthTokenRefreshRequest authTokenRefreshRequest) throws Exception;
	
	User checkAccessToken(OAuthTokenRequest oAuthTokenRequest) throws TokenExpireException, InvalidTokenException;
	
	void toStopList(OAuthTokenRequest oAuthTokenRequest) throws TokenExpireException, InvalidTokenException;
	
	void removeExpiredToken();

}
