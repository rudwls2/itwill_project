package com.itwill.jpa.exception.user;

public class UserNotFoundException extends Exception {
	private Object data;
	public UserNotFoundException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}