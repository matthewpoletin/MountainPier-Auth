package com.mountainpier.auth.web.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AppRequest {
	
	@NotBlank(message = "Property name is not set")
	private String name;
	
	@NotBlank(message = "Property userId is not set")
	@Length(min = 36, max = 36, message = "Not valid UUID")
	private String userId;
	
	private String redirectUri;
	
}
