package com.mountainpier.auth.web.model;

import com.mountainpier.auth.domain.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	
	private String id;
	
	private String username;
	
	public UserResponse(User user) {
		this.id = user.getId().toString();
		this.username = user.getUsername();
	}
	
}
