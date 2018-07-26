package com.v5.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
/**
 * redis工具类
 *
 * @author Jevon
 * @time 2018年7月25日
 * @copyright Jevon & Nate
 */
@Component
public class AuthRedisTemplate {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void set(String key,String value){
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set(key, value);
	}
	
	public void set(String key,String value,long time){
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set(key, value, time, TimeUnit.SECONDS);
	}
	
	public String get(String key){
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		return ops.get(key);
	}
}
