/**
 *
 */
package com.eureka.cms.rs.service.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author User
 *
 */
public class Header implements Serializable {

	private static final long serialVersionUID = 2740281522034352226L;

	private ResultCode status;
	private String code;
	private String message;
	private Map<String, String> messages;

	public enum ResultCode { SUCCESS, FAIL, WARNING }

	public Header(ResultCode code) {
		setStatus(code);
	}

	/**
	 * @return the status
	 */
	public ResultCode getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResultCode status) {
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the messages
	 */
	public Map<String, String> getMessages() {
		if (null == messages){
			setMessages(new HashMap<String, String>());
		}
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


}
