package com.auction.pro.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NavResearchExceptionHandler {
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public static class DataNotFound extends BaseExceptionHandler {
		private static final long serialVersionUID = 4235225697094262603L;

		public DataNotFound(int code, String message, String developerMessage) {
			super(HttpStatus.NOT_FOUND, code, message, developerMessage);
		}
	}

}