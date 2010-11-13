package com.xbean.test.bean.multi;

import com.xbean.annotations.Convertible;
import com.xbean.annotations.Ignore;

public class CompositeClientBean {

	private int id;

	private String string;

	@Convertible("differentName1")
	private int differentName2;

	@Convertible
	private int id2;

	private String string2;

	@Convertible("differentName3")
	private int differentName4;

	@Ignore
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
	public int getDifferentName2() {
		return differentName2;
	}

	/**
	 * @param pDifferentName2
	 *            the differentName2 to set
	 */
	public void setDifferentName2(int pDifferentName2) {
		differentName2 = pDifferentName2;
	}

	/**
	 * @return the id2
	 */
	public int getId2() {
		return id2;
	}

	/**
	 * @param pId2
	 *            the id2 to set
	 */
	public void setId2(int pId2) {
		id2 = pId2;
	}

	/**
	 * @return the string2
	 */
	public String getString2() {
		return string2;
	}

	/**
	 * @param pString2
	 *            the string2 to set
	 */
	public void setString2(String pString2) {
		string2 = pString2;
	}

	/**
	 * @return the differentName4
	 */
	public int getDifferentName4() {
		return differentName4;
	}

	/**
	 * @param pDifferentName4
	 *            the differentName4 to set
	 */
	public void setDifferentName4(int pDifferentName4) {
		differentName4 = pDifferentName4;
	}

	/**
	 * @return the ignoredString
	 */
	public String getIgnoredString() {
		return ignoredString;
	}

	/**
	 * @param pIgnoredString
	 *            the ignoredString to set
	 */
	public void setIgnoredString(String pIgnoredString) {
		ignoredString = pIgnoredString;
	}

}
