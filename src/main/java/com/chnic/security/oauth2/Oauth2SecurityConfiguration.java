package com.chnic.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chnic.security.authentication.UserNamePasswordUserDetailService;

@Profile("oauth2")
@Configuration
@EnableWebSecurity
public class Oauth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserNamePasswordUserDetailService userNamePasswordUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().anyRequest()
			.and().authorizeRequests().antMatchers("/oauth/*").permitAll();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userNamePasswordUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
