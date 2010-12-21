package com.googlecode.xbean.test.bean;

import com.googlecode.xbean.annotations.Convertible;
import com.googlecode.xbean.annotations.Ignore;

public class ServiceBean {

	private int id;

	@Convertible
	private String name;

	@Convertible("differentName2")
	private String differentName1;

	private long longVar;

	@Ignore
	private long ignoredLongVar;
	
	private Boolean someAlwaysNullObj;

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
	 * @return the differentName1
	 */
	public String getDifferentName1() {
		return differentName1;
	}

	/**
	 * @param pDifferentName1
	 *            the differentName1 to set
	 */
	public void setDifferentName1(String pDifferentName1) {
		differentName1 = pDifferentName1;
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

	public void setSomeAlwaysNullObj(Boolean someAlwaysNullObj) {
		this.someAlwaysNullObj = someAlwaysNullObj;
	}

	public Boolean getSomeAlwaysNullObj() {
		return someAlwaysNullObj;
	}

}
