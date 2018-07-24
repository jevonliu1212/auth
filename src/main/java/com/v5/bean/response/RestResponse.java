package com.v5.bean.response;

public class RestResponse<T> {

	private Header header;
	
	private T body;
	
	
	
	public RestResponse() {
		super();
		this.header=new Header();
	}

	public static <T> RestResponse<T> success(){
		return new RestResponse<T>();
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
