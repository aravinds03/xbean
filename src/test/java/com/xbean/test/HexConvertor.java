package com.xbean.test;

import com.xbean.converters.PropertyConverter;

public class HexConvertor implements PropertyConverter<Long, String> {

	public String convert(Long object) throws Exception {
		return Long.toHexString(object.longValue());
	}

}
