package com.v5.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.v5.bean.bo.LoginBO;
import com.v5.bean.bo.UserRegisterBO;
import com.v5.bean.response.RestResponse;
import com.v5.entity.User;
import com.v5.service.UserService;
import com.v5.utils.CookieUtils;

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
		List<User> userList = userService.listUserByMobile(userRegisterBO.getMobile());
		if(!CollectionUtils.isEmpty(userList)){
			return RestResponse.buildWithCodeMsg("20000", "手机号已存在!");
		}
		userService.register(userRegisterBO);
		return RestResponse.success();
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public RestResponse login(@Validated @RequestBody LoginBO loginBO,HttpServletRequest request, HttpServletResponse response){
		List<User> userList = userService.listUserByMobile(loginBO.getMobile());
		if(CollectionUtils.isEmpty(userList)){
			return RestResponse.buildWithCodeMsg("20000", "手机号或密码错误!");
		}
		
		if(userList.size()>1){
			return RestResponse.buildWithCodeMsg("30000", "用户数据异常!");
		}
		
		User currentUser = userList.get(0);
		String pass = DigestUtils.sha256Hex(DigestUtils.sha256Hex(loginBO.getPassword()) + currentUser.getSalt());
		if(!StringUtils.equals(pass, currentUser.getPassword())){
			return RestResponse.buildWithCodeMsg("20000", "手机号或密码错误!");
		}
		CookieUtils.setCookie(response, "user-token", currentUser.getMobile(), 60);
		
		return RestResponse.success();
	}
	
	@RequestMapping(value = "/qqlogin",method = RequestMethod.GET)
	public RestResponse qqlogin(){
		System.out.println("qq login........");
		return RestResponse.success();
	}
}
