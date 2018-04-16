package com.mountainpier.auth.web.model;

import lombok.Data;

@Data
public class OAuthTokenInitialRequest {
	
    private Integer appId;
    
    private String redirectUrl;
    
    private String username;
    
    private String password;
    
}
