package com.v5.bean.bo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * �û�ע��BO
 *
 * @author Jevon
 * @time 2018��7��23��
 * @copyright Jevon & Nate
 */
public class UserRegisterBO {

	/**
	 * �û���
	 */
	@NotBlank(message = "�û�������Ϊ��")
	private String userName;

	/**
	 * ����
	 */
	@NotBlank(message = "���벻��Ϊ��")
	private String password;

	/**
	 * ����
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
