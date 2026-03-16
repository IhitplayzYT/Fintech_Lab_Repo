package com.mit.exception;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7040657621591544806L;
	private final String messageKey;

	private final Object[] messageParams;

	public BusinessException(String messageKey, Object... messageParams) {
		super();
		this.messageKey = messageKey;
		this.messageParams = messageParams;
	}

	public Object[] getMessageParams() {
		return messageParams;
	}

	public String getMessageKey() {
		return messageKey;
	}
}
