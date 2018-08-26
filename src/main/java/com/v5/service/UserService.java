package com.v5.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import com.v5.bean.bo.UserRegisterBO;
import com.v5.bean.response.RestResponse;
import com.v5.entity.User;

/**
 * user业务层
 *
 * @author Jevon
 * @time 2018年7月23日
 * @copyright Jevon & Nate
 */
public interface UserService {

	/**
	 * 用户注册
	 *
	 * @param userRegisterBO
	 * @author Jevon
	 * @time 2018年7月23日
	 */
	void register(UserRegisterBO userRegisterBO);
	
	/**
	 * 根据手机号查询用户
	 *
	 * @param mobile
	 * @return
	 * @author Jevon
	 * @time 2018年7月24日
	 */
	List<User> listUserByMobile(String mobile);
	
	User getUserById(Long id);
	
	RestResponse<String> sendMsgCode(String mobile);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")  
	void test();
}
