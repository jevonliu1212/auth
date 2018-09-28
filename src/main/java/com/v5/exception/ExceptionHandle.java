package com.v5.exception;

import javax.validation.UnexpectedTypeException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.v5.bean.response.RestResponse;
import com.v5.constant.CodeConstants;

/**
 * 异常处理
 *
 * @author Jevon
 * @time 2018年8月26日
 * @copyright Jevon & Nate
 */
@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public RestResponse handle(MethodArgumentNotValidException e){
		System.out.println("exception====================MethodArgumentNotValidException");
		return RestResponse.buildWithCodeMsg(CodeConstants.ECXEPTION_PARAM, e.getBindingResult().getFieldError().getDefaultMessage());
	}
	
	@ExceptionHandler(UnexpectedTypeException.class)
	@ResponseBody
	public RestResponse handle(UnexpectedTypeException e){
		return RestResponse.buildWithCodeMsg(CodeConstants.ECXEPTION_PARAM_TRANSFER, e.getMessage());
	}
	
	
	//@ExceptionHandler(Exception.class)
	//@ResponseBody
	public RestResponse handle(){
		return RestResponse.buildWithCodeMsg(CodeConstants.ECXEPTION, "系统异常");
	}
}
