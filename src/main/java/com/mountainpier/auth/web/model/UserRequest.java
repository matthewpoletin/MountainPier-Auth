package com.mountainpier.auth.web.model;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
	
	@NotNull(message = "Property id is not set")
	String id;
	
	@NotEmpty(message = "Property username is not set")
	String username;
	
	@NotEmpty(message = "Property password is not set")
	String password;
	
}
