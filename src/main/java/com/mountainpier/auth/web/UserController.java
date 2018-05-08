package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.service.UserService;
import com.mountainpier.auth.web.model.AppResponse;
import com.mountainpier.auth.web.model.UserRequest;
import com.mountainpier.auth.web.model.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public UserResponse createUser(@Valid @RequestBody UserRequest userRequest,
								   HttpServletResponse response) throws Exception {
		User user = this.userService.save(userRequest);
		response.addHeader(HttpHeaders.LOCATION, authBaseURI + "/user/" + user.getId());
		return new UserResponse(user);
	}
	
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") UUID userId) {
		this.userService.delete(userId);
	}
	
	@RequestMapping(value = "/user/{userId}/apps", method = RequestMethod.GET)
	public List<AppResponse> getAppsOfUser(@PathVariable("userId") UUID userId) {
		return this.userService.getApps(userId)
			.stream()
			.map(AppResponse::new)
			.collect(Collectors.toList());
	}
	
	
}
