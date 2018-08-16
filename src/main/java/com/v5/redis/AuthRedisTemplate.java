package com.v5.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
/**
 * redis工具类
 *
 * @author Jevon
 * @time 2018年7月25日
 * @copyright Jevon & Nate
 */
@Component
public class AuthRedisTemplate {
	
	private final static String UNLOCK_LUA="if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";

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
	
	public void del(String key){
		stringRedisTemplate.delete(key);
	}
	
	//分布式锁
	public String lock(String key,String value){
		String result = stringRedisTemplate.execute(new RedisCallback<String>(){

			@Override
			public String doInRedis(RedisConnection arg0) throws DataAccessException {
				JedisCommands commands = (JedisCommands) arg0.getNativeConnection();
				return commands.set(key, value, "NX", "PX", 15000);
			}
			
		});
		return result;
	}
	
	public Long unlock(String key,String value){
		Long result = stringRedisTemplate.execute(new RedisCallback<Long>(){
           
			@Override
			public Long doInRedis(RedisConnection arg0) throws DataAccessException {
				 List<String> keys = new ArrayList<>();
		         keys.add(key);
		         List<String> values = new ArrayList<>();
		         values.add(value);
				Object nativeConnection = arg0.getNativeConnection();
				Long o = (Long)((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, values);
				return o;
			}
			
		});
		return result;
	}
	
	public Long lpush(String quene,String msg){
		return stringRedisTemplate.opsForList().leftPush(quene, msg);
	}
	
	public String brpop(String quene){
		String result = stringRedisTemplate.execute(new RedisCallback<String>(){
				@Override
				public String doInRedis(RedisConnection arg0) throws DataAccessException {
					List<byte[]>  list = arg0.bRPop(5, quene.getBytes());
					if(CollectionUtils.isEmpty(list)){
						return null;
					}
					return new String(list.get(1));
				}
				
			});
		return result;
	}
}
