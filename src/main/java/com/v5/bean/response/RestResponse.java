package com.v5.bean.response;

import com.alibaba.druid.util.StringUtils;
import com.v5.constant.CodeConstants;

public class RestResponse<T> {

	private Header header;
	
	private T body;
	
	
	
	public RestResponse() {
		super();
		this.header=new Header();
	}
	
	public RestResponse(T msg) {
		super();
		this.header=new Header();
		this.body=msg;
	}
	
	public RestResponse(String code,String msg){
		super();
		this.header=new Header(code,msg);
	}

	public static <T> RestResponse<T> success(){
		return new RestResponse<T>();
	}
	
	public static <T> RestResponse<T> success(T body){
		return new RestResponse<T>(body);
	}
	
	public static <T> RestResponse<T> buildWithCodeMsg(String code,String msg){
		return new RestResponse<T>(code,msg);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	public String fetchCode(){
		if(this.header==null){
			return null;
		}
		
		return this.header.getCode();
	}
	
	public boolean isSuccess(){
		if(StringUtils.equals(this.fetchCode(), CodeConstants.SUCCESS)){
			return true;
		}
		return false;
	}
	
	public boolean isNotSuccess(){
		return !this.isSuccess();
	}
}
