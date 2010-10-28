package com.xbean.test.bean;

import com.xbean.annotations.Property;


public class ClientBean {
	@Property
	private int integer;
	@Property
	private String string;
	
	public int getInteger() {
		return integer;
	}
	public void setInteger(int integer) {
		this.integer = integer;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
}
