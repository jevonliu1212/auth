package com.v5.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常处理
 *
 * @author Jevon
 * @time 2018年8月26日
 * @copyright Jevon & Nate
 */
@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(Exception.class)
	public String handle(){
		return "errorpage";
	}
}
