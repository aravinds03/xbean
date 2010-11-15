package com.googlecode.xbean.converters.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.googlecode.xbean.converters.PropertyConverter;

/**
 * This is a sample convertor
 * 
 * @author asanthan
 * @version $Revision: 1.0 $
 */
public class DatePropertyConverter implements PropertyConverter<Date, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oauth.common.converter.property.PropertyConverterAdapter#convert(
	 * java.lang.Object)
	 */
	/**
	 * Method convert.
	 * 
	 * @param pObject
	 *            Date
	 * @return String
	 * @throws Exception
	 */
	public String convert(Date pObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(pObject);
	}

}
