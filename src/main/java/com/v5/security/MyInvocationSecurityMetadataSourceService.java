package com.v5.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	private final static Logger log = LoggerFactory.getLogger(MyInvocationSecurityMetadataSourceService.class);
	
	private HashMap<String, Collection<ConfigAttribute>> map =null;
	
	/**
     * 加载权限表中所有权限
     */
	public void loadResourceDefine(){
		map = new HashMap<>();
        Collection<ConfigAttribute> array = new ArrayList<>();
        array.add(new SecurityConfig("ADMIN"));
        Collection<ConfigAttribute> array1 = new ArrayList<>();
        array1.add(new SecurityConfig("ROLE_USER"));
        Collection<ConfigAttribute> array2 = new ArrayList<>();
        array2.add(new SecurityConfig("Test"));
        map.put("/user/**", array);
        map.put("/redis/**", array1);
        map.put("/", array1);
	}
	
	//此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		log.info("getAttributes================");
		if(map ==null) loadResourceDefine();
		//object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
            	log.info("getAttributes======matches=========={}",map.get(resUrl));
                return map.get(resUrl);
            }
        }
        return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
