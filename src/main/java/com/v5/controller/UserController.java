package com.v5.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.v5.bean.bo.UserRegisterBO;
import com.v5.service.UserService;

/**
 * 用户Controller
 *
 * @author Jevon
 * @time 2018年7月23日
 * @copyright Jevon & Nate
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;

	/**
	 * 用户注册
	 *
	 * @param userRegisterBO
	 * @author Jevon
	 * @time 2018年7月23日
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String register(@Validated @RequestBody UserRegisterBO userRegisterBO){
		userService.register(userRegisterBO);
		return "success";
	}
}
