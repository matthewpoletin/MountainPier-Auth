package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.TwitchToken;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.repository.TwitchTokenRepository;
import com.mountainpier.auth.web.model.TwitchTokenRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TwitchTokenServiceImpl implements TwitchTokenService {
	
	private final TwitchTokenRepository twitchTokenRepository;
	
	private final UserServiceImpl userService;
	
	@Autowired
	public TwitchTokenServiceImpl(TwitchTokenRepository twitchTokenRepository, UserServiceImpl userService) {
		this.twitchTokenRepository = twitchTokenRepository;
		this.userService = userService;
	}
	
	@Override
	@Transactional
	public TwitchToken createToken(UUID userId, TwitchTokenRequest tokenRequest) {
		User user = this.userService.getUserById(userId);
		TwitchToken twitchToken = new TwitchToken()
			.setAccessToken(tokenRequest.getAccessToken())
			.setRefreshToken(tokenRequest.getRefreshToken())
			.setExpiresIn(tokenRequest.getExpiresIn())
			.setScope(tokenRequest.getScope());
		twitchToken.setUser(user);
		return this.twitchTokenRepository.save(twitchToken);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TwitchToken getToken(UUID userId) {
		User user = this.userService.getUserById(userId);
		return this.twitchTokenRepository.getTwitchTokenByUser(user);
	}
	
	@Override
	@Transactional
	public void deleteToken(UUID userId) {
		User user = userService.getUserById(userId);
		this.twitchTokenRepository.deleteTwitchTokenByUser(user);
	}
	
}
