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
		TwitchToken token = new TwitchToken()
			.setAccessToken(tokenRequest.getAccessToken())
			.setRefreshToken(tokenRequest.getRefreshToken())
			.setExpiresIn(tokenRequest.getExpiresIn())
			.setScope(tokenRequest.getScope())
			.setTwitchId(tokenRequest.getTwitchId());
		token.setUser(user);
		return this.twitchTokenRepository.save(token);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TwitchToken getTokenByUserId(UUID userId) {
		User user = this.userService.getUserById(userId);
		return this.twitchTokenRepository.getTwitchTokenByUser(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TwitchToken getTwitchTokenByTwitchId(Integer twitchId) {
		return this.twitchTokenRepository.getTwitchTokenByTwitchId(twitchId);
	}
	
	@Override
	@Transactional
	public TwitchToken updateTwitchToken(UUID userId, TwitchTokenRequest tokenRequest) {
		User user = this.userService.getUserById(userId);
		TwitchToken token = this.getTokenByUserId(userId);
		token.setAccessToken(tokenRequest.getAccessToken());
		token.setRefreshToken(tokenRequest.getRefreshToken());
		token.setExpiresIn(tokenRequest.getExpiresIn());
		token.setTwitchId(tokenRequest.getTwitchId() != null ? tokenRequest.getTwitchId() : token.getTwitchId());
		token.setUser(user);
		return this.twitchTokenRepository.save(token);
	}
	
	@Override
	@Transactional
	public void deleteToken(UUID userId) {
		User user = userService.getUserById(userId);
		this.twitchTokenRepository.deleteTwitchTokenByUser(user);
	}
	
	@Override
	@Transactional
	public TwitchToken setTwitchId(UUID userId, Integer twitchId) {
		TwitchToken token = this.getTokenByUserId(userId);
		token.setTwitchId(twitchId);
		return this.twitchTokenRepository.save(token);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUserByTwitchId(Integer twitchId) {
		return this.twitchTokenRepository.getTwitchTokenByTwitchId(twitchId).getUser();
	}
	
}
