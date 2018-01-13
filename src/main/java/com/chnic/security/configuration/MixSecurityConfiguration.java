package com.chnic.security.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chnic.security.authentication.TokenAuthenticationProvider;
import com.chnic.security.authentication.TokenPreAuthenticatedProcessingFilter;
import com.chnic.security.authentication.UserNamePasswordAuthenticationProvider;
import com.chnic.security.authentication.UserNamePasswordAuthenticationSuccessHandler;

@Profile("mix")
@EnableWebSecurity
public class MixSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;

	@Autowired
	private UserNamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.securityContext().disable()
			.requestCache().disable()
			.csrf().disable()
			.sessionManagement().disable()
			.anonymous().disable()
			.formLogin().disable()
			.logout().disable()
			.addFilter(tokenPreAuthenticatedProcessingFilter())
			.addFilter(usernamePasswordAuthenticationFilter())
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

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(tokenAuthenticationProvider)
				.authenticationProvider(usernamePasswordAuthenticationProvider);
	}

	@Bean
	public TokenPreAuthenticatedProcessingFilter tokenPreAuthenticatedProcessingFilter() throws Exception {
		TokenPreAuthenticatedProcessingFilter tokenPreAuthenticatedProcessingFilter = new TokenPreAuthenticatedProcessingFilter();
		tokenPreAuthenticatedProcessingFilter.setAuthenticationManager(authenticationManager());
		return tokenPreAuthenticatedProcessingFilter;
	}

	@Bean
	public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
		UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
		usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
		usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(userNamePasswordAuthenticationSuccessHandler());
		return usernamePasswordAuthenticationFilter;
	}

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserNamePasswordAuthenticationSuccessHandler userNamePasswordAuthenticationSuccessHandler() {
		return new UserNamePasswordAuthenticationSuccessHandler();
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
