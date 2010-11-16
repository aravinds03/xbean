package com.googlecode.xbean.test.bean;

public class ClientBean {

	private int id;

	private String name;

	private String differentName2;

	private long longVar;

	private long ignoredLongVar;

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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param pName
	 *            the name to set
	 */
	public void setName(String pName) {
		name = pName;
	}

	/**
	 * @return the differentName
	 */
	public String getDifferentName() {
		return differentName2;
	}

	/**
	 * @param pDifferentName
	 *            the differentName to set
	 */
	public void setDifferentName(String pDifferentName) {
		differentName2 = pDifferentName;
	}

	/**
	 * @return the longVar
	 */
	public long getLongVar() {
		return longVar;
	}

	/**
	 * @param pLongVar
	 *            the longVar to set
	 */
	public void setLongVar(long pLongVar) {
		longVar = pLongVar;
	}

	/**
	 * @return the ignoredLongVar
	 */
	public long getIgnoredLongVar() {
		return ignoredLongVar;
	}

	/**
	 * @param pIgnoredLongVar
	 *            the ignoredLongVar to set
	 */
	public void setIgnoredLongVar(long pIgnoredLongVar) {
		ignoredLongVar = pIgnoredLongVar;
	}

}
