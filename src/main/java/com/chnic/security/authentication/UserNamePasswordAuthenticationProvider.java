package com.chnic.security.authentication;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.chnic.security.entity.User;
import com.chnic.security.entity.UserRole;
import com.chnic.security.repository.UserRepository;
import com.chnic.security.repository.UserRoleRepository;

@Profile("mix")
@Component
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
		
		String userName = usernamePasswordAuthenticationToken.getName();
		String password = usernamePasswordAuthenticationToken.getCredentials().toString();
		
		User user = userRepository.findOneByUserName(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException("username not found");
		}
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Bad credentials");
		}
		
		List<UserRole> userRoleList = userRoleRepository.findByUserName(userName);
		if (userRoleList == null) {
			throw new InsufficientAuthenticationException("Roles not found");
		}
		
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		Authentication tokenAuthentication = new TokenAuthentication(token); 
		SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
		return tokenAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
