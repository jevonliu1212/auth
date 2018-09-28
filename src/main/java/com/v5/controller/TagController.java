package com.v5.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.v5.bean.bo.SaveTagBO;
import com.v5.bean.response.RestResponse;
import com.v5.service.TagService;

/**
 * tag管理
 *
 * @author Jevon
 * @time 2018年9月28日
 * @copyright Jevon & Nate
 */
@RestController
@RequestMapping("/tag")
public class TagController {

	private final static Logger log = LoggerFactory.getLogger(TagController.class);

	@Resource
	private TagService tagService;
	
	/**
	 * 新增tag
	 *
	 * @param saveTagBO
	 * @return
	 * @author Jevon
	 * @time 2018年9月28日
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public RestResponse saveTag(@Validated @RequestBody SaveTagBO saveTagBO){
		return tagService.saveTag(saveTagBO);
	}
}
