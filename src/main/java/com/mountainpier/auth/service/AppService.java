package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.web.model.AppRequest;

public interface AppService {
	
	App findById(Integer id);
	
	App save(AppRequest appRequest);
	
	void delete(Integer appId);
}
