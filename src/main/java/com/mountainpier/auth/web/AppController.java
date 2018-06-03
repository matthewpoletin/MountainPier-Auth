package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.App;
import com.mountainpier.auth.service.AppService;
import com.mountainpier.auth.web.model.AppRequest;
import com.mountainpier.auth.web.model.AppResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	@RequestMapping(value = "/apps", method = RequestMethod.GET)
	public Page<AppResponse> getApps(@RequestParam(value = "page", defaultValue = "0") Integer page,
									 @RequestParam(value = "size", defaultValue = "25") Integer size) {
		return this.appService.getApps(page, size)
			.map(AppResponse::new);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/apps", method = RequestMethod.POST)
	public AppResponse createApp(@RequestBody @Valid AppRequest appRequest,
								 HttpServletResponse response) {
		App app = this.appService.createApp(appRequest);
		response.addHeader(HttpHeaders.LOCATION, authBaseURI + "/apps/" + app.getId());
		return new AppResponse(app);
	}
	
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/apps/{appId}", method = RequestMethod.GET)
	public AppResponse getApp(@PathVariable("appId") Integer id) {
		return new AppResponse(appService.getAppById(id));
	}
	
	@RequestMapping(value = "/apps/{appId}", method = RequestMethod.PATCH)
	public AppResponse updateApp(@PathVariable("appId") Integer appId,
								 @RequestBody AppRequest appRequest) {
		return new AppResponse(this.appService.updateApp(appId, appRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/apps/{appId}", method = RequestMethod.DELETE)
	public void deleteApp(@PathVariable("appId") Integer appId) {
		appService.deleteApp(appId);
	}
	
}
