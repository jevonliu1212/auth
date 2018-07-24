package com.v5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.v5.entity.User;

@Mapper
public interface UserMapper {

	/**
	 * 保存
	 *
	 * @param user
	 * @return
	 * @author Jevon
	 * @time 2018年7月24日
	 */
	int insert(User user);
	
	/**
	 * 根据主键查询
	 *
	 * @param id
	 * @return
	 * @author Jevon
	 * @time 2018年7月24日
	 */
	User getById(@Param("id")Long id);
	
	/**
	 * 根据手机号查询
	 *
	 * @param mobile
	 * @return
	 * @author Jevon
	 * @time 2018年7月24日
	 */
	List<User> listByMobile(@Param("mobile")String mobile);
}
