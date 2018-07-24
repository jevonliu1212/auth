package com.v5.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.v5.bean.bo.UserRegisterBO;
import com.v5.bean.response.RestResponse;
import com.v5.service.UserService;

/**
 * �û�Controller
 *
 * @author Jevon
 * @time 2018��7��23��
 * @copyright Jevon & Nate
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;

	/**
	 * �û�ע��
	 *
	 * @param userRegisterBO
	 * @author Jevon
	 * @time 2018��7��23��
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public RestResponse register(@Validated @RequestBody UserRegisterBO userRegisterBO){
		userService.register(userRegisterBO);
		return RestResponse.success();
	}
}
