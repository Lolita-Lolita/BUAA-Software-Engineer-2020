package com.demo.config;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demo.entity.User;
import com.demo.repository.UserRepository;

/**
 * 构建spring security标准的认证 Object， 根据自己数据库用户信息可以自定义
 */
@Component
public class UserPermissionDetail implements UserDetailsService {

	private @Autowired UserService repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username)
							.orElseThrow(() -> new RuntimeException("未找到这个用户!"));
		Set<SimpleGrantedAuthority> roles = Stream.of(user.getRole().split(","))
												  .map(role -> "ROLE_" + role)
												  .map(SimpleGrantedAuthority::new)
												  .collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
	}

}
