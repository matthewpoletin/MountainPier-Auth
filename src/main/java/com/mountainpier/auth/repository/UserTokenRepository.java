package com.mountainpier.auth.repository;

import com.mountainpier.auth.domain.UserToken;

import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserTokenRepository extends
		CrudRepository<UserToken, String> {
	
	Optional<UserToken> findByRefreshToken(String refreshToken);
	
	List<UserToken> findAllByAccessExpiresBeforeAndRefreshExpiresBefore(Date accessExpired, Date refreshExpired);

}
