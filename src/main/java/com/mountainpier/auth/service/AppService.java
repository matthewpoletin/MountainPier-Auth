package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.web.model.AppRequest;

import org.springframework.data.domain.Page;

public interface AppService {
	
	Page<App> getApps(Integer page, Integer size);
	
	App findById(Integer id);
	
	App save(AppRequest appRequest);
	
	void delete(Integer appId);
	
}
