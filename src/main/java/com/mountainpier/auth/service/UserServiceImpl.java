package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.exception.UserCredentialsException;
import com.mountainpier.auth.repository.UserRepository;
import com.mountainpier.auth.web.model.UserCredentialsRequest;
import com.mountainpier.auth.web.model.UserRequest;

import com.mountainpier.auth.web.model.UserUpdateRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Page<User> getUsers(Integer page, Integer size) {
		return this.userRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public User getUserById(UUID id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("User with id = " + id.toString() + " not found"));
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findUserByUsername(username)
			.orElseThrow(() -> new EntityNotFoundException("User with username = " + username + " not found"));
	}
	
	@Override
	public User createUser(UserRequest userRequest) throws Exception {
		String salt = RandomStringUtils.randomAlphanumeric(10);
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update((userRequest.getPassword() + salt).getBytes("UTF-8"));
		User user = new User()
				.setId(UUID.fromString(userRequest.getId()))
				.setUsername(userRequest.getUsername())
				.setPassword(DatatypeConverter.printHexBinary(messageDigest.digest()))
				.setSalt(salt)
				.setRole(userRequest.getRole());
		return userRepository.save(user);
	}
	
	@Override
	public User updateUser(UUID userId, UserUpdateRequest userRequest) {
		User user = this.getUserById(userId);
		user.setUsername(userRequest.getUsername() != null ? userRequest.getUsername() : user.getUsername());
		user.setRole(userRequest.getRole() != null ? userRequest.getRole() : user.getRole());
		return this.userRepository.save(user);
	}
	
	@Override
	public User updateCredentials(UUID userId, UserCredentialsRequest userRequest) throws Exception {
		User user = this.getUserById(userId);
		String salt = RandomStringUtils.randomAlphanumeric(10);
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update((userRequest.getPassword() + salt).getBytes("UTF-8"));
		user.setPassword(DatatypeConverter.printHexBinary(messageDigest.digest()));
		user.setSalt(salt);
		return this.userRepository.save(user);
	}
	
	@Override
	public User checkCredentials(String username, String password) throws UserCredentialsException {
		try {
			User user = findByUsername(username);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update((password + user.getSalt()).getBytes("UTF-8"));
			return DatatypeConverter.printHexBinary(messageDigest.digest()).equals(user.getPassword()) ? user : null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserCredentialsException();
		}
	}
	
	@Override
	public void delete(UUID id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public List<App> getApps(UUID userId) {
		return getUserById(userId).getApps();
	}
	
}
