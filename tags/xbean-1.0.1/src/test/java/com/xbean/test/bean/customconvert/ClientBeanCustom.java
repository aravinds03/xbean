package com.xbean.test.bean.customconvert;

import java.util.Date;

public class ClientBeanCustom {

	public ClientBeanCustom(int id, String string, String differentName2, long longVar,
			long ignoredLongVar, long hexLong, String differentNameConvertor, Date dateMember) {
		super();
		this.id = id;
		this.string = string;
		this.differentName2 = differentName2;
		this.longVar = longVar;
		this.ignoredLongVar = ignoredLongVar;
		this.hexLong = hexLong;
		this.differentNameConvertor = differentNameConvertor;
		this.dateMember = dateMember;
	}

	private int id;

	private String string;

	private String differentName2;

	private long longVar;

	private long ignoredLongVar;

	private long hexLong;

	private String differentNameConvertor;

	private Date dateMember;

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
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * @param pString
	 *            the string to set
	 */
	public void setString(String pString) {
		string = pString;
	}

	/**
	 * @return the differentName2
	 */
	public String getDifferentName2() {
		return differentName2;
	}

	/**
	 * @param pDifferentName2
	 *            the differentName2 to set
	 */
	public void setDifferentName2(String pDifferentName2) {
		differentName2 = pDifferentName2;
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

	/**
	 * @return the hexLong
	 */
	public long getHexLong() {
		return hexLong;
	}

	/**
	 * @param pHexLong
	 *            the hexLong to set
	 */
	public void setHexLong(long pHexLong) {
		hexLong = pHexLong;
	}

	/**
	 * @return the differentNameConvertor
	 */
	public String getDifferentNameConvertor() {
		return differentNameConvertor;
	}

	/**
	 * @param pDifferentNameConvertor
	 *            the differentNameConvertor to set
	 */
	public void setDifferentNameConvertor(String pDifferentNameConvertor) {
		differentNameConvertor = pDifferentNameConvertor;
	}

	/**
	 * @return the dateMember
	 */
	public Date getDateMember() {
		return dateMember;
	}

	/**
	 * @param pDateMember
	 *            the dateMember to set
	 */
	public void setDateMember(Date pDateMember) {
		dateMember = pDateMember;
	}

}
