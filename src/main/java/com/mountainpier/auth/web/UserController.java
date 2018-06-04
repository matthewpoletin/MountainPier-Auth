package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.service.UserService;
import com.mountainpier.auth.web.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserController.authBaseURI)
public class UserController {
	
	static final String authBaseURI = "/api/auth";
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public UserResponse createUser(@Valid @RequestBody UserRequest userRequest,
								   HttpServletResponse response) throws Exception {
		User user = this.userService.createUser(userRequest);
		response.addHeader(HttpHeaders.LOCATION, authBaseURI + "/user/" + user.getId());
		return new UserResponse(user);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Page<UserResponse> getUsers(@RequestParam(value = "page", required = false) Integer page,
									   @RequestParam(value = "size", required = false) Integer size) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		return userService.getUsers(page, size)
			.map(UserResponse::new);
	}
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public UserResponse getUser(@PathVariable("userId") UUID userId) {
		return new UserResponse(this.userService.getUserById(userId));
	}
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PATCH)
	public UserResponse updateUser(@PathVariable("userId") UUID userId,
								   @RequestBody @Valid UserUpdateRequest userRequest) {
		return new UserResponse(this.userService.updateUser(userId, userRequest));
	}
	
	@RequestMapping(value = "/users/{userId}/credentials", method = RequestMethod.PATCH)
	public UserResponse updateCredentials(@PathVariable("userId") UUID userId,
										  @RequestBody @Valid UserCredentialsRequest userRequest) throws Exception {
		return new UserResponse(this.userService.updateCredentials(userId, userRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") UUID userId) {
		this.userService.delete(userId);
	}
	
	@RequestMapping(value = "/users/{userId}/apps", method = RequestMethod.GET)
	public List<AppResponse> getAppsOfUser(@PathVariable("userId") UUID userId) {
		return this.userService.getApps(userId)
			.stream()
			.map(AppResponse::new)
			.collect(Collectors.toList());
	}
	
}
