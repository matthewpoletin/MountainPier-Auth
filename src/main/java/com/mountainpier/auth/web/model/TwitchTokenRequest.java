package com.mountainpier.auth.web.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TwitchTokenRequest {
	
	@NotBlank(message = "Property accessToken not set")
	private String accessToken;
	
	@NotBlank(message = "Property refreshToken not set")
	private String refreshToken;
	
	@NotNull(message = "property expiresIn not set")
	private Integer expiresIn;
	
	@NotBlank(message = "Property scope not set")
	private String scope;
	
}
