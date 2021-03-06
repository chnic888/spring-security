package com.chnic.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chnic.security.authentication.UserNamePasswordUserDetailService;

@Profile("authcode")
@Configuration
@EnableWebSecurity
public class OAuth2AuthorizationCodeSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserNamePasswordUserDetailService userNamePasswordUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/login", "/oauth/authorize")
			.and()
			.authorizeRequests().anyRequest().authenticated()
            .and()
			.formLogin().loginPage("/login").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/h2-console/**");
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
