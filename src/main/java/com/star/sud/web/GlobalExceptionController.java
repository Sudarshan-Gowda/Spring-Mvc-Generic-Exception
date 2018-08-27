package com.star.sud.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger logger = Logger.getLogger(BasicController.class);

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		logger.info(ex);

		return "error/exception";

	}

}
