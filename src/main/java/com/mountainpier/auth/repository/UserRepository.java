package com.mountainpier.auth.repository;

import com.mountainpier.auth.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends
		JpaRepository<User, UUID> {
	
	Optional<User> findUserByUsername(String username);
	
}
