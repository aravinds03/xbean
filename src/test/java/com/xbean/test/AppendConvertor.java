package com.xbean.test;

import com.xbean.converters.PropertyConverter;

public class AppendConvertor implements PropertyConverter<String, String> {

	public String convert(String object) throws Exception {
		return object+" converted!!!!";
	}

}
