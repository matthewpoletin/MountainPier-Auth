package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.repository.AppRepository;
import com.mountainpier.auth.web.model.AppRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class AppServiceImpl implements AppService {
	
	private final AppRepository appRepository;
	
	private final UserService userService;
	
	@Autowired
	public AppServiceImpl(AppRepository appRepository,
						  UserService userService) {
		this.appRepository = appRepository;
		this.userService = userService;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<App> getApps(Integer page, Integer size) {
		return this.appRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public App getAppById(Integer id) {
		return appRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("App '{ " + id + " }' not found"));
	}
	
	@Override
	public App createApp(AppRequest appRequest) {
		User user = userService.getUserById(UUID.fromString(appRequest.getUserId()));
		App app = new App()
			.setName(appRequest.getName())
			.setRedirectUri(appRequest.getRedirectUri())
			.setUser(user)
			.setSecret(RandomStringUtils.randomAlphanumeric(50));
		return appRepository.save(app);
	}
	
	@Override
	public App updateApp(Integer appId, AppRequest appRequest) {
		App app = this.appRepository.getOne(appId);
		if (appRequest.getUserId() != null) {
			User user = userService.getUserById(UUID.fromString(appRequest.getUserId()));
			app.setUser(user);
		}
		app.setName(appRequest.getName() != null ? appRequest.getName() : app.getName());
		app.setRedirectUri(appRequest.getRedirectUri() != null ? appRequest.getRedirectUri() : app.getRedirectUri());
		app.setSecret(RandomStringUtils.randomAlphanumeric(50));
		return appRepository.save(app);
	}
	
	@Override
	public void deleteApp(Integer appId) {
		appRepository.deleteById(appId);
	}
	
}
