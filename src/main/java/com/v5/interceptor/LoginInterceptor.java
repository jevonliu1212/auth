package com.v5.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.v5.bean.response.RestResponse;
import com.v5.constant.Constants;
import com.v5.entity.User;
import com.v5.redis.AuthRedisTemplate;
import com.v5.service.UserService;
import com.v5.utils.ContextHolder;
import com.v5.utils.CookieUtils;
/**
 * 登录拦截器
 *
 * @author Jevon
 * @time 2018年7月26日
 * @copyright Jevon & Nate
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private AuthRedisTemplate authRedisTemplate;
	@Resource
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie cookie =CookieUtils.getCookieByName(request, "user-token");
		response.setCharacterEncoding("UTF-8");
		if(cookie==null){
			PrintWriter writer = response.getWriter();
			writer.print(JSON.toJSONString(RestResponse.buildWithCodeMsg("20000", "请先登录!")));
			return false;
		}
		
		String token = cookie.getValue();
		String userId = authRedisTemplate.get(Constants.USER_TOKEN_KEY+token);
		if(userId==null){
			PrintWriter writer = response.getWriter();
			writer.print(JSON.toJSONString(RestResponse.buildWithCodeMsg("20000", "请先登录!")));
			return false;
		}
		User currentUser = userService.getUserById(Long.parseLong(userId));
		if(currentUser==null){
			System.out.println("用户不存在");
		}else{
			ContextHolder.set(currentUser);
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	
}
