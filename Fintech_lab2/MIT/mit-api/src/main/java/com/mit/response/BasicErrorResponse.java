package com.mit.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;


@JsonPropertyOrder({ "code", "userMessage" })
@JsonInclude(value = Include.NON_NULL)
@ApiModel
public class BasicErrorResponse implements Serializable
{

	private static final long serialVersionUID = 1L;

	@JsonProperty("userMessage")
	private String userMessage;

	@JsonProperty("code")
	private String code;

	public BasicErrorResponse()
	{
	}

	public BasicErrorResponse(String code, String userMessage)
	{
		this.code = code;
		this.userMessage = userMessage;
	}

	public String getUserMessage()
	{
		return userMessage;
	}

	public void setUserMessage(String userMessage)
	{
		this.userMessage = userMessage;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

}
