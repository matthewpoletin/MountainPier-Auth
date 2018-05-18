package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.TwitchToken;
import com.mountainpier.auth.web.model.TwitchTokenRequest;

import java.util.UUID;

public interface TwitchTokenService {
	TwitchToken createToken(UUID userId, TwitchTokenRequest tokenRequest);
	TwitchToken getToken(UUID userId);
	void deleteToken(UUID userId);
}
