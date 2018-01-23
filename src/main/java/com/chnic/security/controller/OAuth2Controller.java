package com.chnic.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/oauth2/callback")
public class OAuth2Controller {

	@GetMapping(params = { "code" })
	@ResponseStatus(HttpStatus.OK)
	public String handleCallBack(@RequestParam("code") String code) {
		return code;
	}
}

// 1.Apply for a code
// http://localhost:7070/oauth/authorize?client_id=client3&response_type=code&redirect_uri=http://localhost:7070/oauth2/callback

// 2.Apply for a token by code
// http://localhost:7070/oauth/token?code=DDEp4C&grant_type=authorization_code&client_id=client3&client_secret=password12345&redirect_uri=http://localhost:7070/oauth2/callback

// 3 Access the api by token
// http://localhost:7070/api/hello/nick?access_token=ecfea90a-2ed8-48f3-af66-05d6d07cb766