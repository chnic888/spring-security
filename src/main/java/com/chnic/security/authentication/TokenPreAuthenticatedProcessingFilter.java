package com.chnic.security.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

@Profile("mix")
@Component
public class TokenPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null) {
			return null;
		}
		
		if (!redisTemplate.hasKey(token)) {
			return null;
		}
		
		return token;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return null;
	}
}
