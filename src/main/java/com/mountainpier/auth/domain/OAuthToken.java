package com.mountainpier.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "oauth_token")
public class OAuthToken {
	
	@Id
	@Column(name = "oauth_token_access_token")
	private String accessToken;
	
	@Column(name = "oauth_token_access_expires")
	private Date accessExpires;
	
	@Column(name ="oauth_token_refresh_token", unique = true)
	private String refreshToken;
	
	@Column(name = "oauth_token_refresh_expires")
	private Date refreshExpires;
	
	@Column(name = "oauth_token_app_code")
	private String appCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oauth_token_user")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oauth_token_app")
	private App app;
	
}
