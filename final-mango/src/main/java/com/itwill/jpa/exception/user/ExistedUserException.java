package com.itwill.jpa.exception.user;

public class ExistedUserException extends Exception{
	
	private Object data;
	public ExistedUserException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}