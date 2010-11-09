package com.xbean.test.bean.multi;

public class ServiceBeanOne {
	private int integer;
	private String string;
	private int differentName1;
	
	private String ignoredString;
	
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
	public void setIgnoredString(String ignoredString) {
		this.ignoredString = ignoredString;
	}
	public String getIgnoredString() {
		return ignoredString;
	}
	
}
