package com.mountainpier.auth.web.model;

import com.mountainpier.auth.domain.Role;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class UserUpdateRequest {
	
	@NotEmpty(message = "Property username is not set")
	String username;
	
	Role role;
	
}

