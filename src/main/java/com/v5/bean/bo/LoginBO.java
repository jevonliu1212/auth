package com.v5.bean.bo;

import org.hibernate.validator.constraints.NotBlank;

public class LoginBO {

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String password;
	
	/**
	 * 手机号
	 */
	@NotBlank(message = "手机号不能为空")
	private String mobile;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
