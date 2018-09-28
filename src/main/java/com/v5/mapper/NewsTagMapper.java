package com.v5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.v5.entity.NewsTag;
@Mapper
public interface NewsTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsTag record);

    int insertSelective(NewsTag record);

    NewsTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsTag record);

    int updateByPrimaryKey(NewsTag record);
}