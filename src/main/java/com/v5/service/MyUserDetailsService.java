package com.v5.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.v5.bean.security.MyUserDetail;

@Component
public class MyUserDetailsService implements UserDetailsService {

	private final static Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("用户名是：{}",username);
		User user = new User(username,passwordEncoder.encode("1234"),AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return user;
	}

}
