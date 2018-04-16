package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.web.model.UserRequest;

public interface UserService {
	
	User findByUsername(String username);
	
	User checkCredentials(String username, String password) throws UserCredentialsException;
	
	User save(UserRequest userRequest) throws Exception;
	
	void delete(Integer id);
	
}
