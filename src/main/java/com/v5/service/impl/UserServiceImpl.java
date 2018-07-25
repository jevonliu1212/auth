package com.v5.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.v5.bean.bo.UserRegisterBO;
import com.v5.entity.User;
import com.v5.mapper.UserMapper;
import com.v5.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserMapper userMapper;
	/**
	 * �û�ע��
	 *
	 * @param userRegisterBO
	 * @author Jevon
	 * @time 2018��7��23��
	 */
	public void register(UserRegisterBO userRegisterBO) {
		User user = new User();
		BeanUtils.copyProperties(userRegisterBO, user);
		String salt = UUID.randomUUID().toString();
		String pass = DigestUtils.sha256Hex(DigestUtils.sha256Hex(userRegisterBO.getPassword()) + salt);
		user.setPassword(pass);
		user.setSalt(salt);
		userMapper.insert(user);
	}
	
	
	public List<User> listUserByMobile(String mobile) {
		return userMapper.listByMobile(mobile);
	}

}
