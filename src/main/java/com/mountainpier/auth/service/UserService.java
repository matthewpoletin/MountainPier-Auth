package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.web.model.UserCredentialsRequest;
import com.mountainpier.auth.web.model.UserRequest;

import com.mountainpier.auth.web.model.UserUpdateRequest;
import org.springframework.data.domain.Page;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public interface UserService {
	
	Page<User> getUsers(Integer page, Integer size);
	
	User getUserById(UUID id);
	
	User findByUsername(String username);
	
	User save(UserRequest userRequest) throws Exception;
	
	User updateUser(UUID userId, UserUpdateRequest userRequest);
	
	User updateCredentials(UUID userId, UserCredentialsRequest userRequest) throws Exception;
	
	User checkCredentials(String username, String password) throws UserCredentialsException;
	
	void delete(UUID id);
	
	List<App> getApps(UUID userId);

}
