package com.v5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.v5.entity.Source;
@Mapper
public interface SourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Source record);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
}