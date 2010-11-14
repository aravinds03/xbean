package com.xbean.converters.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xbean.converters.PropertyConverter;

/**
 * This is a sample convertor
 *
 */
public class DatePropertyConverter implements PropertyConverter<Date, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oauth.common.converter.property.PropertyConverterAdapter#convert(
	 * java.lang.Object)
	 */
	@Override
	public String convert(Date pObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(pObject);
	}

}
