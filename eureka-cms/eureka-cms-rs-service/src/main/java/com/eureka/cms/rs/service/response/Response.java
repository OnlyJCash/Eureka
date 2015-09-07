/**
 *
 */
package com.eureka.cms.rs.service.response;

import java.io.Serializable;

import com.eureka.cms.rs.service.response.Header.ResultCode;


/**
 * @author mmazzilli
 *
 */
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 8740225158353994642L;

	private Header header;
	private T body;

	public Response(ResultCode code) {
		setHeader(new Header(code));
	}

	public static <T> Response<T> ok() {
		return new Response<T>(ResultCode.SUCCESS);
	}

	public static <T> Response<T> fail(){
		return new Response<T>(ResultCode.FAIL);
	}

	public static <T> Response<T> warning(){
		return new Response<T>(ResultCode.WARNING);
	}

	private void setHeader(Header header){
		this.header = header;
	}

	/**
	 * @return the body
	 */
	public T getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public Response<T> withBody(T entity) {
		this.body = entity;
		return this;
	}

	public Response<T> addHeaderMessage(String key, String value){
		this.getHeader().getMessages().put(key, value);
		return this;
	}

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}

	/**
	 *
	 * @param message
	 * @return
	 */
	public Response<T> withHeaderMessage(String message){
		this.getHeader().setMessage(message);
		return this;
	}

	/**
	 *
	 * @param code
	 * @return
	 */
	public Response<T> withHeaderCode(String code){
		this.getHeader().setCode(code);
		return this;
	}
}
