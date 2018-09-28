package com.v5.service;

import com.v5.bean.bo.SaveTagBO;
import com.v5.bean.response.RestResponse;

/**
 * tag管理
 *
 * @author Jevon
 * @time 2018年9月28日
 * @copyright Jevon & Nate
 */
public interface TagService {

	/**
	 * 新增tag
	 *
	 * @param saveTagBO
	 * @return
	 * @author Jevon
	 * @time 2018年9月28日
	 */
	RestResponse saveTag(SaveTagBO saveTagBO);
}
