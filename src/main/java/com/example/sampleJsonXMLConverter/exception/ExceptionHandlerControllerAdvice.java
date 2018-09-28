package com.example.sampleJsonXMLConverter.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.sampleJsonXMLConverter.exception.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	private static Logger logger = LogManager.getLogger(ExceptionHandlerControllerAdvice.class);

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ValidationResponse handleValidationException(final ValidationException exception,
			final HttpServletRequest request) {

		logger.debug("Inside ExceptionHandlerControllerAdvice:handleValidationException -"+exception.getMessage());
		List<String> errMsg = new ArrayList<String>();
	    StringTokenizer st = new StringTokenizer(exception.getMessage(),"|");
		
		ValidationResponse error = new ValidationResponse();
	    while (st.hasMoreElements()) {
	    	errMsg.add((String)st.nextElement());
		}
	    error.setErrorMessages(errMsg);
//		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());

		return error;
	}

	@ExceptionHandler(CustomException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleCustomException(final CustomException exception,
			final HttpServletRequest request) {

		logger.debug("Inside ExceptionHandlerControllerAdvice:CustomException -"+exception.getMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());

		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {

		logger.debug("Inside ExceptionHandlerControllerAdvice: handleException -"+exception.getMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());
		return error;
	}
}