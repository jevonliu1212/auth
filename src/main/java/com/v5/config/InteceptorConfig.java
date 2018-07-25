package com.v5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.v5.interceptor.LoginInterceptor;


@Configuration
public class InteceptorConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/*").excludePathPatterns("/user/login","/user/register");
		super.addInterceptors(registry);
	}

}
