package com.mountainpier.auth.web;

import com.mountainpier.auth.domain.TwitchToken;
import com.mountainpier.auth.service.TwitchTokenServiceImpl;
import com.mountainpier.auth.web.model.TwitchTokenRequest;
import com.mountainpier.auth.web.model.TwitchTokenResponse;
import com.mountainpier.auth.web.model.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(TwitchTokenController.twitchBaseURI)
public class TwitchTokenController {
	
	static final String twitchBaseURI = "/api/auth/providers";
	
	private final TwitchTokenServiceImpl twitchTokenService;
	
	@Autowired
	public TwitchTokenController(TwitchTokenServiceImpl twitchTokenService) {
		this.twitchTokenService= twitchTokenService;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/twitch/users/{userId}", method = RequestMethod.POST)
	public TwitchTokenResponse createTokenOfUser(@PathVariable("userId") UUID userId,
												 @RequestBody @Valid TwitchTokenRequest tokenRequest,
												 HttpServletResponse response) {
		TwitchToken token = this.twitchTokenService.createToken(userId, tokenRequest);
		response.addHeader(HttpHeaders.LOCATION, twitchBaseURI+ "/twitch/users/" + token.getUser().getId().toString());
		return new TwitchTokenResponse(token);
	}
	
	@RequestMapping(value = "/twitch/users/{userId}", method = RequestMethod.GET)
	public TwitchTokenResponse getTokenByUserId(@PathVariable("userId") UUID userId) {
		return new TwitchTokenResponse(this.twitchTokenService.getTokenByUserId(userId));
	}
	
	@RequestMapping(value = "/twitch/id/{twitchId}", method = RequestMethod.GET)
	public TwitchTokenResponse getTwitchTokenByTwitchId(@PathVariable("twitchId") Integer twitchId) {
		return new TwitchTokenResponse(this.twitchTokenService.getTwitchTokenByTwitchId(twitchId));
	}
	
	@RequestMapping(value = "/twitch/users/{userId}", method = RequestMethod.PATCH)
	public TwitchTokenResponse updateToken(@PathVariable("userId") UUID userId,
										   @RequestBody @Valid TwitchTokenRequest tokenRequest) {
		return new TwitchTokenResponse(this.twitchTokenService.updateTwitchToken(userId, tokenRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/twitch/users/{userId}", method = RequestMethod.DELETE)
	public void deleteToken(@PathVariable("userId") UUID userId) {
		this.twitchTokenService.deleteToken(userId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/twitch/users/{userId}/{twitchId}", method = RequestMethod.POST)
	public TwitchTokenResponse setTwitchId(@PathVariable("userId") UUID userId,
										   @PathVariable("twitchId") Integer twitchId) {
		return new TwitchTokenResponse(this.twitchTokenService.setTwitchId(userId, twitchId));
	}
	
	@RequestMapping(value = "/twitch/id/{twitchId}/user", method = RequestMethod.GET)
	public UserResponse getUserByTwitchId(@PathVariable("twitchId") Integer twitchId) {
		return new UserResponse(this.twitchTokenService.getUserByTwitchId(twitchId));
	}
	
}
