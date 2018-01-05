package com.chnic.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class TokenAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private String token;

	public TokenAuthentication(String token) {
		super(null);
        super.setAuthenticated(true);
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

}
