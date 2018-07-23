package com.v5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.v5.entity.User;

@Mapper
public interface UserMapper {

	int insert(User user);
}
