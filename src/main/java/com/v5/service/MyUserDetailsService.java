package com.v5.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	//	authorities.add(new SimpleGrantedAuthority("REDIS"));
		User user = new User(username,"1234",authorities);
		return user;
	}

}
