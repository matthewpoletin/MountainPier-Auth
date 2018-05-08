package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.web.model.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
	
	User getUserById(UUID id);
	
	User findByUsername(String username);
	
	User checkCredentials(String username, String password) throws UserCredentialsException;
	
	User save(UserRequest userRequest) throws Exception;
	
	void delete(UUID id);
	
	List<App> getApps(UUID userId);
}
