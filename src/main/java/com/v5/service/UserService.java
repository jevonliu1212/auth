package com.v5.service;

import com.v5.bean.bo.UserRegisterBO;

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
}
