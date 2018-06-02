package com.mountainpier.auth.repository;

import com.mountainpier.auth.domain.App;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends
		JpaRepository<App, Integer> {
}
