package com.v5.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.v5.bean.bo.UserRegisterBO;
import com.v5.bean.response.RestResponse;
import com.v5.config.QCloudMsgConfig;
import com.v5.entity.User;
import com.v5.mapper.UserMapper;
import com.v5.service.UserService;

@Service
@Transactional
@EnableConfigurationProperties({QCloudMsgConfig.class})
public class UserServiceImpl implements UserService {
	
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserMapper userMapper;
	@Resource
	private QCloudMsgConfig qCloudMsgConfig;
	/**
	 * 用户注册
	 *
	 * @param userRegisterBO
	 * @author Jevon
	 * @time 2018年7月23日
	 */
	public void register(UserRegisterBO userRegisterBO) {
		User user = new User();
		BeanUtils.copyProperties(userRegisterBO, user);
		String salt = UUID.randomUUID().toString();
		String pass = DigestUtils.sha256Hex(DigestUtils.sha256Hex(userRegisterBO.getPassword()) + salt);
		user.setPassword(pass);
		user.setSalt(salt);
		userMapper.insert(user);
	}
	
	/**
	 * 根据手机号查询用户
	 *
	 * @param mobile
	 * @return
	 * @author Jevon
	 * @time 2018年7月24日
	 */
	public List<User> listUserByMobile(String mobile) {
		return userMapper.listByMobile(mobile);
	}

	@Override
	public User getUserById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public RestResponse<String> sendMsgCode(String mobile) {
		String code=this.getRandNum(6);
		try {
			int appid=Integer.parseInt(qCloudMsgConfig.getAppid());
			int templateId=Integer.parseInt(qCloudMsgConfig.getTemplate());
//			String sign = qCloudMsgConfig.getSign();
//			sign = new String(sign.getBytes(),"utf-8");
		    String[] params = {code,"1"};
		    String[] phoneNumbers = {mobile};
		    SmsMultiSender msender = new SmsMultiSender(appid, qCloudMsgConfig.getAppkey());
		    SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,templateId, params, "", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    System.out.println(result);
		    int res = result.result;
		    //发送失败
		    if(res!=0){
		    	return RestResponse.buildWithCodeMsg("20000", result.errMsg);
		    }
		} catch (Exception e) {
		    log.error("sendmsg.err={}",e);
		    return RestResponse.buildWithCodeMsg("30000", "短信发送异常");
		} 
		
		return RestResponse.success(code);
	}

	
	public String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
    public int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
