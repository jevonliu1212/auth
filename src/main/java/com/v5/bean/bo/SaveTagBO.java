package com.v5.bean.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增tag
 *
 * @author Jevon
 * @time 2018年9月28日
 * @copyright Jevon & Nate
 */
public class SaveTagBO {

	/**
	 * tag名称
	 */
	@NotBlank(message = "tagName不能为空")
	private String tagName;

	/**
	 * tag类型（0-球队，1-球星，2-其他）
	 */
	@NotNull(message = "tagType不能为空")
	private Integer tagType;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getTagType() {
		return tagType;
	}

	public void setTagType(Integer tagType) {
		this.tagType = tagType;
	}

}
