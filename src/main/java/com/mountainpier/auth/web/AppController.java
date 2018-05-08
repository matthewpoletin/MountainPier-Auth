package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.service.AppService;
import com.mountainpier.auth.web.model.AppRequest;
import com.mountainpier.auth.web.model.AppResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(AppController.authBaseURI)
public class AppController {
	
	static final String authBaseURI = "/api/auth";
	
	private final AppService appService;
	
	@Autowired
	public AppController(AppService appService) {
		this.appService = appService;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/app", method = RequestMethod.POST)
	public AppResponse createApp(@Valid @RequestBody AppRequest appRequest, HttpServletResponse response) {
		App app = this.appService.save(appRequest);
		response.addHeader(HttpHeaders.LOCATION, authBaseURI + "/app/" + app.getId());
		return new AppResponse(app);
	}
	
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/app/{id}", method = RequestMethod.GET)
	public AppResponse getApp(@PathVariable Integer id) {
		return new AppResponse(appService.findById(id));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/app/{appId}", method = RequestMethod.DELETE)
	public void deleteApp(@PathVariable("appId") Integer appId) {
		appService.delete(appId);
	}
	
}
