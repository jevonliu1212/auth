package com.v5.bean.response;

public class RestResponse<T> {

	private Header header;
	
	private T body;
	
	
	
	public RestResponse() {
		super();
		this.header=new Header();
	}
	
	public RestResponse(String code,String msg){
		super();
		this.header=new Header(code,msg);
	}

	public static <T> RestResponse<T> success(){
		return new RestResponse<T>();
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
	
	
}
