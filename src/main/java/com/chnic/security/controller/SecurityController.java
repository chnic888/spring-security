package com.chnic.security.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
public class SecurityController {
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView home(Map<String, Object> model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("message", "Hello " + name);
		model.put("title", "Hello Home");
		model.put("date", new Date());
		return new ModelAndView("home");
	}
	
	@GetMapping(value = "/logout")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView logout(Map<String, Object> model) {
		SecurityContextHolder.clearContext();;
		return new ModelAndView("login");
	}
}
