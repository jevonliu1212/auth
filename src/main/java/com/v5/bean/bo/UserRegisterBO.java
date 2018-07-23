package com.v5.bean.bo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户注册BO
 *
 * @author Jevon
 * @time 2018年7月23日
 * @copyright Jevon & Nate
 */
public class UserRegisterBO {

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	private String userName;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String password;

	/**
	 * 邮箱
	 */
	private String email;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
