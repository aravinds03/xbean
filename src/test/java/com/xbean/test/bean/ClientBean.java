package com.xbean.test.bean;



public class ClientBean {
	
	private int integer;
	
	private String string;
	
	private int differentName2;
	
	private long longVar;
	
	private long ignoredLongVar;
	
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
	public void setLongVar(long longVar) {
		this.longVar = longVar;
	}
	public long getLongVar() {
		return longVar;
	}
	public void setIgnoredLongVar(long ignoredLongVar) {
		this.ignoredLongVar = ignoredLongVar;
	}
	public long getIgnoredLongVar() {
		return ignoredLongVar;
	}
	
}
