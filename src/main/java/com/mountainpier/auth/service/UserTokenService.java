package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.domain.UserToken;
import com.mountainpier.auth.exception.InvalidTokenException;
import com.mountainpier.auth.exception.TokenExpireException;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.web.model.UserTokenInitialRequest;
import com.mountainpier.auth.web.model.UserTokenRefreshRequest;
import com.mountainpier.auth.web.model.UserTokenRequest;

public interface UserTokenService {
	
	UserToken create(UserTokenInitialRequest userTokenInitialRequest) throws UserCredentialsException;
	
	UserToken refresh(UserTokenRefreshRequest userTokenRefreshRequest) throws TokenExpireException, InvalidTokenException;
	
	User checkAccessToken(UserTokenRequest userTokenRequest) throws TokenExpireException, InvalidTokenException;
	
	void delete(UserTokenRequest userTokenRequest);
	
	void removeExpiredToken();

}
