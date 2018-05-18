package com.mountainpier.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "twitch_token")
public class TwitchToken {
	
	@Id
	@Column(name = "twitch_token_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "twitch_token_access_token", nullable = false)
	private String accessToken;
	
	@Column(name = "twitch_token_refresh_token", nullable = false)
	private String refreshToken;
	
	// Number of seconds until the token expires
	@Column(name = "twitch_token_expires_in", nullable = false)
	private Integer expiresIn;
	
	@Column(name = "twitch_token_scope")
	private String scope;
	
	@OneToOne
	@JoinColumn(name = "twitch_token_user_id", nullable = false, unique = true)
	private User user;
	
}
