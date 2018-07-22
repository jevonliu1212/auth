package com.v5.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * �û�ʵ����
 *
 * @author Jevon
 * @time 2018��7��22��
 * @copyright Jevon & Nate
 */
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * �û���
	 */
	private String userName;

	/**
	 * ����
	 */
	private String password;

	/**
	 * ����
	 */
	private String email;

	/**
	 * ����
	 */
	private String salt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
