package com.chnic.security.authentication;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

@Profile("mix")
@Component
public class UserNamePasswordAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
    protected ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
		response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Map<String, String> resultMap = Maps.newHashMap();
        resultMap.put("token", tokenAuthentication.getPrincipal().toString());
        response.getWriter().write(objectMapper.writeValueAsString(resultMap));
		clearAuthenticationAttributes(request);
	}

}
