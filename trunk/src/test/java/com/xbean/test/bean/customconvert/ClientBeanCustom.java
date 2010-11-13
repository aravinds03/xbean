package com.xbean.test.bean.customconvert;

import java.util.Date;



public class ClientBeanCustom {
	
	public ClientBeanCustom(int integer, String string, int differentName2,
			long longVar, long ignoredLongVar,long hexLong, String differentNameConvertor,
			Date dateMember) {
		super();
		this.integer = integer;
		this.string = string;
		this.differentName2 = differentName2;
		this.longVar = longVar;
		this.ignoredLongVar = ignoredLongVar;
		this.hexLong = hexLong;
		this.differentNameConvertor = differentNameConvertor;
		this.dateMember = dateMember;
	}
	private int integer;
	
	private String string;
	
	private int differentName2;
	
	private long longVar;
	
	private long ignoredLongVar;
	
	private long hexLong;
	
	private String differentNameConvertor;
	
	private Date dateMember;
	
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
	public void setHexLong(long hexLong) {
		this.hexLong = hexLong;
	}
	public long getHexLong() {
		return hexLong;
	}
	public void setDifferentNameConvertor(String differentNameConvertor) {
		this.differentNameConvertor = differentNameConvertor;
	}
	public String getDifferentNameConvertor() {
		return differentNameConvertor;
	}
	public void setDateMember(Date dateMember) {
		this.dateMember = dateMember;
	}
	public Date getDateMember() {
		return dateMember;
	}
	
	
}
