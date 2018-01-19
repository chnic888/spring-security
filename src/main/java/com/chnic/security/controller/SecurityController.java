
package com.chnic.security.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

@RestController
public class SecurityController {

	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	@GetMapping(value = "/")
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
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		if (redisTemplate.hasKey(token)) {
			redisTemplate.delete(token);
		}
		SecurityContextHolder.clearContext();
		return new ModelAndView("login");
	}
	
	@GetMapping(value = "/login")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView login(Map<String, Object> model) {
		return new ModelAndView("login");
	}
	
	@GetMapping(value = "/detail")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView detail(Map<String, Object> model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("title", name.toUpperCase() + " Details: ");
		model.put("message", "Fake Detail...");
		return new ModelAndView("detail");
	}
	
	@GetMapping(value = "/test")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> test() {
		HashMap<String, String> map = Maps.newHashMap();
		map.put("label", "Success");
		return map;
	}

}
