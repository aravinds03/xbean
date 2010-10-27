package com.xbean.converters.impl;

import java.util.Date;

import com.xbean.converters.PropertyConverterAdapter;

public class DatePropertyConverter extends PropertyConverterAdapter<Date, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oauth.common.converter.property.PropertyConverterAdapter#convert(
	 * java.lang.Object)
	 */
	@Override
	public String convert(Date pObject) throws Exception {
		return pObject.toString();
	}

}
