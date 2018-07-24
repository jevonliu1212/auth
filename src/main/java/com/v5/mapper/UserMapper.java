package com.v5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.v5.entity.User;

@Mapper
public interface UserMapper {

	/**
	 * ����
	 *
	 * @param user
	 * @return
	 * @author Jevon
	 * @time 2018��7��24��
	 */
	int insert(User user);
	
	/**
	 * ����������ѯ
	 *
	 * @param id
	 * @return
	 * @author Jevon
	 * @time 2018��7��24��
	 */
	User getById(@Param("id")Long id);
	
	/**
	 * �����ֻ��Ų�ѯ
	 *
	 * @param mobile
	 * @return
	 * @author Jevon
	 * @time 2018��7��24��
	 */
	List<User> listByMobile(@Param("mobile")String mobile);
}
