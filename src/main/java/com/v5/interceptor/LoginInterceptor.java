package com.v5.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.v5.redis.AuthRedisTemplate;
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
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie cookie =CookieUtils.getCookieByName(request, "user-token");
		
		if(cookie==null){
			PrintWriter writer = response.getWriter();
			writer.print("请先登录!");
			return false;
		}
		
		String token = cookie.getValue();
		String userId = authRedisTemplate.get("user-token-"+token);
		if(userId==null){
			PrintWriter writer = response.getWriter();
			writer.print("请先登录!");
			return false;
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
