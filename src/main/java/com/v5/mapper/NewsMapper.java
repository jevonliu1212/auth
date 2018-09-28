package com.v5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.v5.entity.News;
@Mapper
public interface NewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}