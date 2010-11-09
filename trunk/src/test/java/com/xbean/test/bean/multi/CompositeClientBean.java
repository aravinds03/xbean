package com.xbean.test.bean.multi;

import com.xbean.annotations.Convertible;
import com.xbean.annotations.Ignore;


public class CompositeClientBean {
	@Convertible
	private int integer;

	private String string;
	@Convertible("differentName1")
	private int differentName2;
	
	@Convertible
	private int integer2;
	
	private String string2;
	
	@Convertible("differentName3")
	private int differentName4;
	
	@Ignore
	private String ignoredString;
	
	public int getInteger2() {
		return integer2;
	}
	public void setInteger2(int integer2) {
		this.integer2 = integer2;
	}
	public String getString2() {
		return string2;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public int getDifferentName4() {
		return differentName4;
	}
	public void setDifferentName4(int differentName4) {
		this.differentName4 = differentName4;
	}
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
	public void setIgnoredString(String ignoredString) {
		this.ignoredString = ignoredString;
	}
	public String getIgnoredString() {
		return ignoredString;
	}
	
}
