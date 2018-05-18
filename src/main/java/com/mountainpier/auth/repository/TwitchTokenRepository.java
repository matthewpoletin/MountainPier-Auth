package com.mountainpier.auth.repository;

import com.mountainpier.auth.domain.TwitchToken;
import com.mountainpier.auth.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitchTokenRepository extends
		JpaRepository<TwitchToken, Integer> {
	
	TwitchToken getTwitchTokenByUser(User user);
	
	void deleteTwitchTokenByUser(User user);
	
}
