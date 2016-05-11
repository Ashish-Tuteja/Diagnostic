package com.auction.pro.common.exception;

import org.springframework.http.HttpStatus;

//suitable exception types only
public class BaseExceptionHandler extends RuntimeException {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	// Each exception message will be hold here
	private HttpStatus status;
	private int code;
	private String message;
	private String developerMessage;

	public BaseExceptionHandler(HttpStatus staus, int code, String message,
			String developerMessage) {
		this.status = staus;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{\"status\":" + status + ",\"code\":" + code
				+ ",\"message\":\"" + message + "\",\"developerMessage\":\""
				+ developerMessage + "\"}";
	}

	public static void main(String[] args) {
		BaseExceptionHandler baseExceptionHandler = new BaseExceptionHandler(
				HttpStatus.ACCEPTED, 12345, "This is exception message",
				"This is developer message");
		System.out.println(baseExceptionHandler);
	}
}
