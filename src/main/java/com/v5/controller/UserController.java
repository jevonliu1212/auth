package com.v5.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.v5.bean.bo.LoginBO;
import com.v5.bean.bo.LoginByMsgCodeBO;
import com.v5.bean.bo.SendMsgCodeBO;
import com.v5.bean.bo.UserRegisterBO;
import com.v5.bean.response.RestResponse;
import com.v5.constant.Constants;
import com.v5.entity.User;
import com.v5.redis.AuthRedisTemplate;
import com.v5.service.UserService;
import com.v5.utils.CookieUtils;

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
	
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private AuthRedisTemplate authRedisTemplate;
	/**
	 * 用户注册
	 *
	 * @param userRegisterBO
	 * @return
	 * @author Jevon
	 * @time 2018年7月23日
	 */
	@RequestMapping(value = "/nl/register",method = RequestMethod.POST)
	public RestResponse register(@Validated @RequestBody UserRegisterBO userRegisterBO){
		List<User> userList = userService.listUserByMobile(userRegisterBO.getMobile());
		if(!CollectionUtils.isEmpty(userList)){
			return RestResponse.buildWithCodeMsg("20000", "手机号已存在!");
		}
		userService.register(userRegisterBO);
		return RestResponse.success();
	}
	
	/**
	 * 手机号和密码登录
	 *
	 * @param loginBO
	 * @param request
	 * @param response
	 * @return
	 * @author Jevon
	 * @time 2018年7月25日
	 */
	@RequestMapping(value = "/nl/login",method = RequestMethod.POST)
	public RestResponse login(@Validated @RequestBody LoginBO loginBO, HttpServletResponse response){
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
		
		//保存redis和cookies
		String token = DigestUtils.md5Hex(currentUser.getMobile()+UUID.randomUUID().toString());
		log.info("user-token=========={}",token);
		CookieUtils.setCookie(response, "user-token", token, 1800);
		authRedisTemplate.set(Constants.USER_TOKEN_KEY+token, Long.toString(currentUser.getId()),1800L);
		
		return RestResponse.success();
	}
	
	@RequestMapping(value = "/nl/sendMsgCode",method = RequestMethod.POST)
	public RestResponse sendMsgCode(@Validated @RequestBody SendMsgCodeBO sendMsgCodeBO){
		List<User> userList = userService.listUserByMobile(sendMsgCodeBO.getMobile());
		if(CollectionUtils.isEmpty(userList)){
			return RestResponse.buildWithCodeMsg("20000", "用户不存在");
		}
		
		if(userList.size()>1){
			return RestResponse.buildWithCodeMsg("30000", "用户数据异常!");
		}
		RestResponse<String> response = userService.sendMsgCode(sendMsgCodeBO.getMobile());
		if(response.isNotSuccess()){
			return response;
		}
		//发送redis
		authRedisTemplate.set(Constants.MSG_CODE_KEY+sendMsgCodeBO.getMobile(), response.getBody(),600L);
		return RestResponse.success();
	}
	
	@RequestMapping(value = "/nl/loginWithCode",method = RequestMethod.POST)
	public RestResponse login(@Validated @RequestBody LoginByMsgCodeBO loginByMsgCodeBO, HttpServletResponse response){
		List<User> userList = userService.listUserByMobile(loginByMsgCodeBO.getMobile());
		if(CollectionUtils.isEmpty(userList)){
			return RestResponse.buildWithCodeMsg("20000", "用户不存在");
		}
		
		if(userList.size()>1){
			return RestResponse.buildWithCodeMsg("30000", "用户数据异常!");
		}
		
		String code = authRedisTemplate.get(Constants.MSG_CODE_KEY+loginByMsgCodeBO.getMobile());
		if(!StringUtils.equals(loginByMsgCodeBO.getMsgCode(), code)){
			return RestResponse.buildWithCodeMsg("20000", "验证码错误!");
		}
		
		User currentUser = userList.get(0);
		
		// 保存redis和cookies
		String token = DigestUtils.md5Hex(currentUser.getMobile() + UUID.randomUUID().toString());
		log.info("user-token=========={}", token);
		CookieUtils.setCookie(response, "user-token", token, 1800);
		authRedisTemplate.set(Constants.USER_TOKEN_KEY + token, Long.toString(currentUser.getId()), 1800L);
		return RestResponse.success();
	}
	
	@RequestMapping(value = "/nl/qqlogin",method = RequestMethod.GET)
	public RestResponse qqlogin(){
		return RestResponse.success();
	}
	
	
}
