package com.chnic.security.authentication;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.chnic.security.entity.User;
import com.chnic.security.entity.UserRole;
import com.chnic.security.repository.UserRepository;
import com.chnic.security.repository.UserRoleRepository;

@Component
public class UserNamePasswordUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("Invalid UserName");
		}

		User user = userRepository.findOneByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("UserName does not exist");
		}

		List<UserRole> userRoleList = userRoleRepository.findByUserName(username);
		List<GrantedAuthority> grantedAuthorityList = userRoleList.stream()
				.map(userRole -> new SimpleGrantedAuthority(userRole.getRole())).collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorityList);
	}

}
