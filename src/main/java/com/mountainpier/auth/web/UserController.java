package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.service.UserService;
import com.mountainpier.auth.web.model.UserRequest;
import com.mountainpier.auth.web.model.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id) {
		this.userService.delete(id);
	}
	
}
