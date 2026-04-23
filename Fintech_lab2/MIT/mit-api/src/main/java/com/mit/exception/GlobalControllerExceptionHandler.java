package com.mit.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	private static String BUSINESSEXCEPTIONKEY = "business.error.";

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException e) {
		String key = BUSINESSEXCEPTIONKEY + e.getMessageKey();
		String localizedMessage = messageSource.getMessage(key, e.getMessageParams(), Locale.ENGLISH);
		return new ResponseEntity<>(new ApiError(localizedMessage, key), HttpStatus.EXPECTATION_FAILED);
	}

	public class ApiError {

		private String errorMessage;
		private String errorCode;

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public ApiError(String errorMessage, String errorCode) {
			super();
			this.errorMessage = errorMessage;
			this.errorCode = errorCode;
		}

	}

}