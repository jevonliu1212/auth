package com.v5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.v5.entity.UserTag;
@Mapper
public interface UserTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserTag record);

    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);
}