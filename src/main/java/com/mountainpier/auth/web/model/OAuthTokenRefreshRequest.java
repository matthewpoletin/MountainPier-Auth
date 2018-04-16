package com.mountainpier.auth.web.model;

import lombok.Data;

@Data
public class OAuthTokenRefreshRequest {
	
    private String refreshToken;
    
}
