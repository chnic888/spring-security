package com.chnic.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class GreetingController {

	@GetMapping(value = "/hello/{name}")
	@ResponseStatus(HttpStatus.OK)
	public String hello(@PathVariable("name") String name) {
		return "Hello " + name;
	}

	@GetMapping(value = "/goodbye")
	@ResponseStatus(HttpStatus.OK)
	public String bye() {
		return "Bye";
	}
	
	@GetMapping(value = "/goodbye/{name}")
	@ResponseStatus(HttpStatus.OK)
	public String goodbye(@PathVariable("name") String name) {
		return "Goodbye " + name;
	}

}
