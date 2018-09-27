//package com.v5.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//
//import com.v5.security.MyFilterSecurityInterceptor;
//import com.v5.service.MyUserDetailsService;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)  
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
//
//     @Bean
//    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
//        return new MyUserDetailsService();
//    }
//     
//     @Bean
//     public SessionRegistry getSessionRegistry(){
//         SessionRegistry sessionRegistry=new SessionRegistryImpl();
//         return sessionRegistry;
//     }
//     
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.inMemoryAuthentication()
////        .withUser("admin").password("admin").roles("ADMIN")
////        .and()
////        .withUser("terry").password("terry").roles("USER")
////        .and()
////        .withUser("larry").password("larry").roles("USER");
//		auth.userDetailsService(customUserService());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//        .anyRequest().authenticated() //任何请求,登录后可以访问
//        .and()
//        .formLogin()
//        .loginPage("/login")
//        .failureUrl("/login?error")
//        .permitAll() //登录页面用户任意访问
//        .and()
//        .logout().permitAll()
//        .and()//注销行为任意访问
//        .csrf().disable()
//        .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).sessionRegistry(getSessionRegistry());          // 关闭csrf防护
//		
//		 http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// TODO Auto-generated method stub
//		super.configure(web);
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	     return new BCryptPasswordEncoder();
//	 }
//
//}
