package com.xbean.test.bean;

import com.xbean.annotations.Convertible;
import com.xbean.annotations.Ignore;

public class ServiceBean {
	@Convertible
	private int integer;
	@Convertible
	private String string;
	@Convertible("differentName2")
	private int differentName1;
	
	private long longVar;
	
	@Ignore
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
	public void setDifferentName1(int differentName1) {
		this.differentName1 = differentName1;
	}
	public int getDifferentName1() {
		return differentName1;
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
