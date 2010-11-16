package com.googlecode.xbean.test.bean.customconvert;

import com.googlecode.xbean.annotations.Convertible;
import com.googlecode.xbean.annotations.Ignore;
import com.googlecode.xbean.converters.impl.DatePropertyConverter;
import com.googlecode.xbean.test.AppendConvertor;
import com.googlecode.xbean.test.HexConvertor;

public class ServiceBeanCustom {

	private int id;

	private String string;

	@Convertible("differentName2")
	private String differentName1;

	@Convertible(convertor = HexConvertor.class)
	private String hexLong;

	private long longVar;

	@Convertible(value = "differentNameConvertor", convertor = AppendConvertor.class)
	private String diffNameConvertor123;

	@Convertible(value = "dateMember", convertor = DatePropertyConverter.class)
	private String diffNameConvertorFromDate;

	@Ignore
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
	 * @return the hexLong
	 */
	public String getHexLong() {
		return hexLong;
	}

	/**
	 * @param pHexLong
	 *            the hexLong to set
	 */
	public void setHexLong(String pHexLong) {
		hexLong = pHexLong;
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
	 * @return the diffNameConvertor123
	 */
	public String getDiffNameConvertor123() {
		return diffNameConvertor123;
	}

	/**
	 * @param pDiffNameConvertor123
	 *            the diffNameConvertor123 to set
	 */
	public void setDiffNameConvertor123(String pDiffNameConvertor123) {
		diffNameConvertor123 = pDiffNameConvertor123;
	}

	/**
	 * @return the diffNameConvertorFromDate
	 */
	public String getDiffNameConvertorFromDate() {
		return diffNameConvertorFromDate;
	}

	/**
	 * @param pDiffNameConvertorFromDate
	 *            the diffNameConvertorFromDate to set
	 */
	public void setDiffNameConvertorFromDate(String pDiffNameConvertorFromDate) {
		diffNameConvertorFromDate = pDiffNameConvertorFromDate;
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
