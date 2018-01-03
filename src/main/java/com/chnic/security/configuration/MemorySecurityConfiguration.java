package com.chnic.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("memory")
@EnableWebSecurity
@Configuration
public class MemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().fullyAuthenticated()
			.and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
			.and().logout().logoutUrl("/logout").permitAll();
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
    	 web.ignoring().antMatchers("/css/**", "/h2-console/**");
    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin")
				.roles("ADMIN", "USER").and().withUser("user").password("user")
				.roles("USER");
	}
}
