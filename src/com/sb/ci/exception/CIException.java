package com.sb.ci.exception;

public class CIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CIException(Exception e) {
		super.initCause(e);
	}

	public CIException(String message) {
		super(message);
	}
}
