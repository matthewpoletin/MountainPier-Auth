package com.mountainpier.auth.web.model;

import com.mountainpier.auth.domain.TwitchToken;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TwitchTokenResponse {

	private String accessToken;
	
	private String refreshToken;
	
	private Integer expiresIn;
	
	private String scope;

	private UUID userId;
	
	private Integer twitchId;
	
	public TwitchTokenResponse(TwitchToken twitchToken) {
		this.accessToken = twitchToken.getAccessToken();
		this.refreshToken = twitchToken.getRefreshToken();
		this.expiresIn = twitchToken.getExpiresIn();
		this.scope = twitchToken.getScope();
		this.userId = twitchToken.getUser().getId();
		this.twitchId = twitchToken.getTwitchId();
	}
	
}
