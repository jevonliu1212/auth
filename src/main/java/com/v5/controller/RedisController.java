package com.v5.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.v5.bean.response.RestResponse;
import com.v5.redis.AuthRedisTemplate;

@RestController
@RequestMapping("/redis")
public class RedisController {

	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private AuthRedisTemplate authRedisTemplate;
	
	@RequestMapping(value = "/nl/redisQuene",method = RequestMethod.GET)
	public void redisQuene(){
		while(true){
			log.info("接收到新信息:{}",authRedisTemplate.brpop("quene"));
		}
		
	}
	
	@RequestMapping(value = "/nl/qqlogin",method = RequestMethod.GET)
	public RestResponse qqlogin(){
		String lockKey = "lock";
		String lockValue = UUID.randomUUID().toString();
		String result = authRedisTemplate.lock(lockKey,lockValue);
		//null表示已被锁住，ok表示拿到锁
		if(result==null){
			return RestResponse.buildWithCodeMsg("20000", "获取锁失败");
		}
		System.out.println("qq login........"+result+",value="+lockValue);
		
		try {
			Thread.sleep(30000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("del lock........");
		//authRedisTemplate.del(lockKey);
		Long unlockResult =  authRedisTemplate.unlock(lockKey, lockValue);
		log.info("unlockResult=========={}", unlockResult);
		if(unlockResult==0L){
			return RestResponse.buildWithCodeMsg("20000", "释放锁失败");
		}
		log.info("释放完毕");
		return RestResponse.success();
	}
}
