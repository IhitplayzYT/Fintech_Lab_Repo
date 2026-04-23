package com.mit.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder({ "success", "result" })
public class BasicResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private ResultResponse result;
	private boolean success = false;

	public BasicResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** Gets the result response object */
	public final ResultResponse getResult() {
		return result;
	}

	public final void setResult(ResultResponse result) {
		this.result = result != null ? result : new ResultResponse();
	}

	public final boolean isSuccess() {
		return success;
	}

	public void setResponse(Number code, String userMessage, String systemMessage) {
		this.result = new ResultResponse();
		this.result.setSystemMessage(systemMessage);
		this.result.setInfo(userMessage);
	}

	public void setResponseOK() {
		success = true;
		setResponse(HttpStatus.OK.value(), "Request processed successfully.", null);
	}

	public void setResponseOK(String userMessage) {
		success = true;
		setResponse(HttpStatus.OK.value(), userMessage, null);
	}

	public void setResponseError(String userMessage) {
		success = false;
		setResponse(HttpStatus.OK.value(), userMessage, null);
	}

	public void setResponseOK(String userMessage, String systemMessage) {
		success = true;
		setResponse(HttpStatus.OK.value(), userMessage, systemMessage);
	}

	public void setResponseRedirect() {
		final HttpStatus httpStatus = HttpStatus.FOUND;
		setResponse(httpStatus.value(), "Request processed successfully.", null);
	}

	public void setResponseCreated() {
		final HttpStatus httpStatus = HttpStatus.CREATED;
		setResponse(httpStatus.value(), httpStatus.getReasonPhrase(), null);
	}

	public void setResponseCreated(String info) {
		final HttpStatus httpStatus = HttpStatus.CREATED;
		setResponse(httpStatus.value(), info, null);

	}

	public void setResponseBadRequest(String errorDescription) {
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		setResponse(httpStatus.value(), httpStatus.getReasonPhrase(), "bad_request");
	}

	public void setResponseUnauthorized() {
		final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		setResponse(httpStatus.value(), httpStatus.getReasonPhrase(), "unathorized");
	}

	public void setResponseForbidden() {
		final HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		setResponse(httpStatus.value(), httpStatus.getReasonPhrase(), "forbidden");
	}

	public void setResponseConflict() {
		final HttpStatus httpStatus = HttpStatus.CONFLICT;
		setResponse(httpStatus.value(), httpStatus.getReasonPhrase(), "conflict");
	}

	public void setResponseNotFound() {
		final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		setResponse(httpStatus.value(), httpStatus.getReasonPhrase(), "not_found");
	}

	public void setResponseInternalServerError() {
		final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		setResponse(httpStatus.value(), httpStatus.getReasonPhrase(), "server_error");
	}

}
