package com.mountainpier.auth.repository;

import com.mountainpier.auth.domain.App;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends
		CrudRepository<App, Integer> {
}
