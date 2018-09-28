package com.v5.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.v5.bean.bo.SaveTagBO;
import com.v5.bean.response.RestResponse;
import com.v5.constant.CodeConstants;
import com.v5.entity.Tag;
import com.v5.mapper.TagMapper;
import com.v5.service.TagService;
/**
 * tag管理
 *
 * @author Jevon
 * @time 2018年9月28日
 * @copyright Jevon & Nate
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {
	
	@Resource
	private TagMapper tagMapper;

	/**
	 * 新增tag
	 *
	 * @param saveTagBO
	 * @return
	 * @author Jevon
	 * @time 2018年9月28日
	 */
	@Override
	public RestResponse saveTag(SaveTagBO saveTagBO) {
		//判断tag是否已存在
		Tag t = tagMapper.getTagByName(saveTagBO.getTagName());
		if(t != null){
			return RestResponse.buildWithCodeMsg(CodeConstants.BUSINESS_ERROR, "tag已存在");
		}
		Tag tag = new Tag();
		tag.setTagName(saveTagBO.getTagName());
		tag.setTagType(saveTagBO.getTagType());
		tag.setCreateTime(new Date());
		tag.setDeleteFlag(1);
		tagMapper.insert(tag);
		return RestResponse.success();
	}

}
