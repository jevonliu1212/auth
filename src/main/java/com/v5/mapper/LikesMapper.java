package com.v5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.v5.entity.Likes;
@Mapper
public interface LikesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
}