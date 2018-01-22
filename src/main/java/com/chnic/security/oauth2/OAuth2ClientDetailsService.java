package com.chnic.security.oauth2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import com.chnic.security.entity.AuthorizedUser;
import com.chnic.security.entity.AuthorizedUserRole;
import com.chnic.security.repository.AuthorizedUserRepository;
import com.chnic.security.repository.AuthorizedUserRoleRepository;
import com.google.common.collect.Lists;

@Profile({"oauth2", "authcode"})
@Component
public class OAuth2ClientDetailsService implements ClientDetailsService {

	@Autowired
	private AuthorizedUserRepository authorizedUserRepository;
	
	@Autowired
	private AuthorizedUserRoleRepository authorizedUserRoleRepository;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		AuthorizedUser authorizedUser = authorizedUserRepository.findOneByClientId(clientId);
		if (authorizedUser == null) {
			return null;
		} 
		
		BaseClientDetails baseClientDetails = new BaseClientDetails();
		baseClientDetails.setResourceIds(AuthorizationServerConfiguration.RESOURCE_ID_LIST);
		baseClientDetails.setClientId(authorizedUser.getClientId());
		baseClientDetails.setClientSecret(authorizedUser.getClientSecret());
		baseClientDetails.setScope(Lists.newArrayList(authorizedUser.getScope().split(",")));
		baseClientDetails.setAuthorizedGrantTypes(Lists.newArrayList(authorizedUser.getAuthorizedGrantType().split(",")));
		
		List<AuthorizedUserRole> authorizedUserRoleList = authorizedUserRoleRepository.findByClientId(clientId);
		List<SimpleGrantedAuthority> authorityList = authorizedUserRoleList.stream()
				.map(authorizedUserRole -> new SimpleGrantedAuthority(authorizedUserRole.getRole()))
				.collect(Collectors.toList());
		baseClientDetails.setAuthorities(authorityList);
		baseClientDetails.setAccessTokenValiditySeconds(60);
		
		return baseClientDetails;
	}

}
