package com.v5.bean.response;

import com.v5.constant.CodeConstants;

/**
 * 消息返回头
 * 
 * @time 2018年7月24日
 * @author ljw
 * @copyright Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
public class Header {

	private String code = CodeConstants.SUCCESS;
	
	private String message ="success";

	
	public Header() {
		super();
	}

	public Header(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
