package com.v5.bean.bo;

import org.hibernate.validator.constraints.NotBlank;

public class SendMsgCodeBO {

	/**
	 * 手机号
	 */
	@NotBlank(message = "手机号不能为空")
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
