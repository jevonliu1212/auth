package com.v5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("admin").password("admin").roles("ADMIN")
        .and()
        .withUser("terry").password("terry").roles("USER")
        .and()
        .withUser("larry").password("larry").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
        .loginPage("/user/userlogin").permitAll()           // 设置登录页面
        //.loginProcessingUrl("/user/nl/userlogin")  // 自定义的登录接口
        .and()
        .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
        .antMatchers("/userlogin.html").permitAll()     // 设置所有人都可以访问登录页面
        .antMatchers("/user/**").hasRole("ADMIN")
        .antMatchers("/redis/**").hasRole("USER")
        .anyRequest()               // 任何请求,登录后可以访问
        .authenticated()
        .and()
        .csrf().disable();          // 关闭csrf防护
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	     return new BCryptPasswordEncoder();
	 }

}
