package com.xbean.test.bean;

import com.xbean.annotations.Convertible;


public class ClientBean {
	
	private int integer;
	
	private String string;
	
	private int differentName2;
	
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
	public void setDifferentName2(int differentName2) {
		this.differentName2 = differentName2;
	}
	public int getDifferentName2() {
		return differentName2;
	}
	
}
