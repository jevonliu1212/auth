package com.v5.service;

import java.util.List;

import com.v5.bean.bo.UserRegisterBO;
import com.v5.entity.User;

/**
 * userҵ���
 *
 * @author Jevon
 * @time 2018��7��23��
 * @copyright Jevon & Nate
 */
public interface UserService {

	/**
	 * �û�ע��
	 *
	 * @param userRegisterBO
	 * @author Jevon
	 * @time 2018��7��23��
	 */
	void register(UserRegisterBO userRegisterBO);
	
	/**
	 * �����ֻ��Ų�ѯ�û�����
	 *
	 * @param mobile
	 * @return
	 * @author Jevon
	 * @time 2018��7��24��
	 */
	List<User> listUserByMobile(String mobile);
}
