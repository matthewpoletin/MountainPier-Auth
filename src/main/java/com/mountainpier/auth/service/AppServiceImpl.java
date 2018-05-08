package com.mountainpier.auth.service;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.domain.User;
import com.mountainpier.auth.repository.AppRepository;
import com.mountainpier.auth.web.model.AppRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class AppServiceImpl implements AppService {
	
	private final AppRepository appRepository;
	
	private final UserService userService;
	
	@Autowired
	public AppServiceImpl(AppRepository appRepository,
						  UserServiceImpl userService) {
		this.appRepository = appRepository;
		this.userService = userService;
	}
	
	@Override
	public App findById(Integer id) {
		return appRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("App '{ " + id + " }' not found"));
	}
	
	@Override
	public App save(AppRequest appRequest) {
		User user = userService.getUserById(UUID.fromString(appRequest.getUserId()));
		App app = new App()
			.setName(appRequest.getName())
			.setSecret(RandomStringUtils.randomAlphanumeric(50))
			.setUser(user);
		return appRepository.save(app);
	}
}
