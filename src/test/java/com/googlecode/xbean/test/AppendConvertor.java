package com.googlecode.xbean.test;

import com.googlecode.xbean.converters.PropertyConverter;

/**
 * A sample property convertor which appends " converted!!!!" string 
 * to the given input string.
 * @author spradeep
 *
 */
public class AppendConvertor implements PropertyConverter<String, String> {

	public String convert(String object) throws Exception {
		return object+" converted!!!!";
	}

}
