package com.chnic.security.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.chnic.security.authentication.TokenAuthenticationFilter;

@Profile("token")
@EnableWebSecurity
@Configuration
public class TokenSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenAuthenticationFilter tokenAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.securityContext().disable()
			.requestCache().disable()
			.csrf().disable()
			.sessionManagement().disable()
			.anonymous().disable()
			.formLogin().disable()
			.logout().disable()
			.addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(authorizedEntryPoint())
			.accessDeniedHandler(accessDeniedHandler());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/h2-console/**", "/login*");
	}
	
	@Bean
	public AuthenticationEntryPoint authorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return (request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
}
