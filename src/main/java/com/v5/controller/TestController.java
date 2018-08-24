package com.v5.controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.v5.bean.response.RestResponse;
import com.v5.bean.security.Msg;
import com.v5.redis.AuthRedisTemplate;

@Controller
//@RequestMapping("/test")
public class TestController {

	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@RequestMapping("/")
    public String index(Model model){
		log.info("index request...");
        Msg msg =  new Msg("测试标题","测试内容1","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }
    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }
    
    @RequestMapping("/cleansession")
    public String cleansession(){
    	 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	 String username = user.getUsername();
    	 log.info(JSON.toJSONString(user));
    	 List<SessionInformation> sessionList = sessionRegistry.getAllSessions(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), false);
         log.info("session size :"+sessionList.size());
    	 if(!CollectionUtils.isEmpty(sessionList)){
        	 for(SessionInformation session : sessionList){
        		 session.expireNow();
        	 }
         }
    	 return "login";
    }
    
    @RequestMapping(value = "/login")
    //@ResponseBody
	public String login() {
		System.out.println("+++++++++++++++++");
		return "login";
	}
}
