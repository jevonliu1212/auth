package com.v5.bean.bo;

import org.hibernate.validator.constraints.NotBlank;

public class LoginByMsgCodeBO {

	/**
	 * 手机号
	 */
	@NotBlank(message = "手机号不能为空")
	private String mobile;
	
	/**
	 * 验证码
	 */
	@NotBlank(message = "验证码不能为空")
	private String msgCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
	
}
