package com.googlecode.xbean.test.bean.multi;

public class ServiceBeanOne {
	private int id;

	private String string;

	private int differentName1;

	private String ignoredString;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param pId
	 *            the id to set
	 */
	public void setId(int pId) {
		id = pId;
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
