package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.TwitchToken;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.web.model.TwitchTokenRequest;

import java.util.UUID;

public interface TwitchTokenService {
	TwitchToken createToken(UUID userId, TwitchTokenRequest tokenRequest);
	TwitchToken getTokenByUserId(UUID userId);
	TwitchToken getTwitchTokenByTwitchId(Integer twitchId);
	TwitchToken updateTwitchToken(UUID userId, TwitchTokenRequest tokenRequest);
	void deleteToken(UUID userId);
	
	TwitchToken setTwitchId(UUID userId, Integer twitchId);
	
	User getUserByTwitchId(Integer twitchId);
}
