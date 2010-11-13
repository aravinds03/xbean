package com.xbean.test.bean.customconvert;

import com.xbean.annotations.Converter;
import com.xbean.annotations.Convertible;
import com.xbean.annotations.Ignore;
import com.xbean.converters.impl.DatePropertyConverter;
import com.xbean.test.AppendConvertor;
import com.xbean.test.HexConvertor;

public class ServiceBeanCustom {
	
	
//	public ServiceBeanCustom(int integer, String string, int differentName1,
//			String hexLong, long longVar, long ignoredLongVar) {
//		super();
//		this.integer = integer;
//		this.string = string;
//		this.differentName1 = differentName1;
//		this.hexLong = hexLong;
//		this.longVar = longVar;
//		this.ignoredLongVar = ignoredLongVar;
//	}
	public ServiceBeanCustom() {
	}
	@Convertible
	private int integer;
	@Convertible
	private String string;
	@Convertible("differentName2")
	private int differentName1;
	
	@Convertible(convertor = HexConvertor.class)
	private String hexLong;
	
	private long longVar;
	
	@Convertible(value="differentNameConvertor",convertor = AppendConvertor.class)
	private String diffNameConvertor123;
	// FIXME: If a different member needs same member value, the it breaks!
//	@Convertible(value="differentNameConvertor",convertor = AppendConvertor.class)
//	private String diffNameConvertorAnother;
	
	@Convertible(value="dateMember",convertor = DatePropertyConverter.class)
	private String diffNameConvertorFromDate;
	
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
	public void setHexLong(String hexLong) {
		this.hexLong = hexLong;
	}
	public String getHexLong() {
		return hexLong;
	}
	public void setDiffNameConvertor123(String diffNameConvertor123) {
		this.diffNameConvertor123 = diffNameConvertor123;
	}
	public String getDiffNameConvertor123() {
		return diffNameConvertor123;
	}
	
//	public void setDiffNameConvertorAnother(String diffNameConvertorAnother) {
//		this.diffNameConvertorAnother = diffNameConvertorAnother;
//	}
//	public String getDiffNameConvertorAnother() {
//		return diffNameConvertorAnother;
//	}
	public void setDiffNameConvertorFromDate(String diffNameConvertorFromDate) {
		this.diffNameConvertorFromDate = diffNameConvertorFromDate;
	}
	public String getDiffNameConvertorFromDate() {
		return diffNameConvertorFromDate;
	}
	
}
