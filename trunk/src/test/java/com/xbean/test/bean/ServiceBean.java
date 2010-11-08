package com.xbean.test.bean;

import com.xbean.annotations.Convertible;

public class ServiceBean {
	@Convertible
	private int integer;
	@Convertible
	private String string;
	@Convertible(alias="differentName2")
	private int differentName1;
	
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
	public void setDifferentName1(int differentName1) {
		this.differentName1 = differentName1;
	}
	public int getDifferentName1() {
		return differentName1;
	}
	
}
