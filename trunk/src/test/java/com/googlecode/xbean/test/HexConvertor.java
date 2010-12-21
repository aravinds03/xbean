package com.googlecode.xbean.test;

import com.googlecode.xbean.converters.PropertyConverter;

/**
 * A sample property convertor to convert a Long into a hex encode string.
 * 
 * Eg. conver(15) returns "F"
 * @author spradeep
 *
 */
public class HexConvertor implements PropertyConverter<Long, String> {

	public String convert(Long object) throws Exception {
		return Long.toHexString(object.longValue());
	}

}
